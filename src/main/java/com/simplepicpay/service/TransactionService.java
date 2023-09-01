package com.simplepicpay.service;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simplepicpay.domain.transaction.Transaction;
import com.simplepicpay.domain.user.User;
import com.simplepicpay.dto.TransactionDTO;
import com.simplepicpay.respositories.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserService userService;
	
	public void createTransaction(TransactionDTO transactionDTO) throws Exception {
		User payer = userService.findUserById(transactionDTO.payer().getId());
		User payee = userService.findUserById(transactionDTO.payee().getId());
		
		boolean isAuthorized = authorizeTransaction(payer, transactionDTO.value());
		
		if(!isAuthorized) {
			throw new Exception("Não autorizado");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setPayerId(payer.getId());
		newTransaction.setPayeeId(payee.getId());
		newTransaction.setAmount(transactionDTO.value());
		newTransaction.setTransactionDate(new Date());
		
		
	}
	
	private boolean authorizeTransaction(User payer, BigDecimal amount) throws Exception {
		return userService.hasPermissionToTransfer(null) 
				&& userService.accountBalanceIsEnough(payer, amount)
				&& authorizedByService();
	}
	
	public boolean authorizedByService() {
		ResponseEntity<Map> response = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
		
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			return true;
		}
		
		return false;
	}

}

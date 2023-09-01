package com.simplepicpay.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplepicpay.domain.user.User;
import com.simplepicpay.domain.user.UserType;
import com.simplepicpay.dto.UserDTO;
import com.simplepicpay.respositories.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean hasPermissionToTransfer(User payer) throws Exception {
		if(!payer.getUserType().equals(UserType.COMMON)) {
			throw new Exception("Usuário não tem permissão para fazer transferências!");
		}
		return true;
	}
	
	public boolean accountBalanceIsEnough(User payer, BigDecimal amount) throws Exception{
		if(payer.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo insuficiente para realizar a transação!");
		}
		return true;
	}
	
	public User findUserById(Long id) throws Exception {
		return userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não cadastrado."));
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public User createUser(UserDTO userDTO) {
		return this.userRepository.save(new User(userDTO));
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

}

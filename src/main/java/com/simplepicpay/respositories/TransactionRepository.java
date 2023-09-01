package com.simplepicpay.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplepicpay.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}

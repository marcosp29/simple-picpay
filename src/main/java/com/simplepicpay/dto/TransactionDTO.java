package com.simplepicpay.dto;

import java.math.BigDecimal;

import com.simplepicpay.domain.user.User;

public record TransactionDTO(User payer, User payee, BigDecimal value) {

}

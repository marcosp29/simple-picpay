package com.simplepicpay.dto;

import java.math.BigDecimal;

import com.simplepicpay.domain.user.UserType;

public record UserDTO(
		String firstName, 
		String lastName,
		String email,
		String password,
		Long document,
		BigDecimal balance,
		UserType userType) {

}

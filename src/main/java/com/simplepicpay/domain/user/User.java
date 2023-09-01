package com.simplepicpay.domain.user;

import java.math.BigDecimal;

import com.simplepicpay.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstname")
    private String firstName;
    
	@Column(name = "lastName")
    private String lastName;
    
    @Column(name = "email", unique = true)
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "document", unique = true)
    private Long document;
    
    @Column(name = "balance")
    private BigDecimal balance;
    
    @Column(name = "userType")
    private UserType userType;
    
	public User(UserDTO userDTO) {
		this.firstName = userDTO.firstName();
		this.lastName = userDTO.lastName();
		this.email = userDTO.email();
		this.password = userDTO.password();
		this.document = userDTO.document();
		this.balance = userDTO.balance();
		this.userType = userDTO.userType();
	}
    
}

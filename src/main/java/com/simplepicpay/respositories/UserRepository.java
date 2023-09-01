package com.simplepicpay.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplepicpay.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long>{

}

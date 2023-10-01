package com.demo.hackyeah.repository;

import com.demo.hackyeah.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MessageRepository extends JpaRepository<Message, Long> {
}

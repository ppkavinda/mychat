package com.adnivak.mychat.demo.repository;

import com.adnivak.mychat.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}

package com.example.demo.repositories;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    public List<Message> findMessagesByEmploye(Employe emp);

    public  List<Message> findMessagesByDateenvoi(Date date);
}

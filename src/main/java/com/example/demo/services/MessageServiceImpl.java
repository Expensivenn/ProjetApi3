package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Message;
import com.example.demo.repositories.EmployeRepository;
import com.example.demo.repositories.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class MessageServiceImpl  implements InterfMessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Message create(Message msg) throws Exception {
        messageRepository.save(msg);
        return msg;
    }

    @Override
    public Message read(Integer id) throws Exception {
        return messageRepository.findById(id).get();
    }

    @Override
    public Message update(Message msg) throws Exception {
        read(msg.getId_message());
        messageRepository.save(msg);
        return msg;
    }

    @Override
    public void delete(Message msg) throws Exception {
        messageRepository.deleteById(msg.getId_message());
    }

    @Override
    public List<Message> all() throws Exception {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getMessagesEmp(Employe emp) {
        List<Message> lcf = messageRepository.findMessagesByEmploye(emp);
        return lcf;
    }

    @Override
    public List<Message> getMessagesDate(Date date) {
        List<Message> lcf = messageRepository.findMessagesByDateenvoi(date);
        return lcf;
    }

}


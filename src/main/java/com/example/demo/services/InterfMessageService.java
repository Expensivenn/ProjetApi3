package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Message;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
public interface InterfMessageService extends InterfServices<Message>{
    List<Message> getMessagesEmp(Employe employe);

    List<Message> getMessagesDate(Date date);

    List<Message> getMessagesBetweenTwoDates(Date date1,Date date2,Employe emp);


}

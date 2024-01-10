package com.example.demo.repositories;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    public List<Message> findMessagesByEmploye(Employe emp);

    public  List<Message> findMessagesByDateenvoi(Date date);
    @Query("SELECT m FROM Message m " +
            "WHERE m.dateenvoi BETWEEN :startDate AND :endDate AND m.employe = :employe")
    List<Message> findMessagesBetweenTwoDates(@Param("startDate") Date startDate,
                                              @Param("endDate") Date endDate,
                                              @Param("employe") Employe employe);
}


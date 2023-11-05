package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MessageServiceImplTest {
    @Autowired
    private InterfEmployeService employeServiceImpl;

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    Message message;
    Employe emp;

    @BeforeEach
    void setUp() {
        try{
            emp = new Employe(null,"test@test.com","NomTest","PrenomTest",1,new ArrayList<>());
            employeServiceImpl.create(emp);
            System.out.println("création de l'employe : "+emp);
            message = new Message(null,"ObjetTest","ContenuTest",new Date(2000,1,1),null,emp);
            messageServiceImpl.create(message);
            System.out.println("création du message : "+message);
        }
        catch (Exception e){
            System.out.println("erreur de création du message : "+message +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            messageServiceImpl.delete(message);
            employeServiceImpl.delete(emp);
            System.out.println("effacement du messgage : "+message);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement du message :"+message+" erreur : "+e);
        }
    }
    @Test
    void read() {
        try{
            int id=message.getId_message();
            Message message1 = messageServiceImpl.read(id);
            assertEquals("ObjetTest",message1.getObjet(),"Objets différents "+"ObjetTest"+"-"+message1.getObjet());
            assertEquals("ContenuTest",message1.getContenu(),"Contenus différents "+"ContenuTest"+"-"+message1.getContenu());
            assertEquals(new Date(2000,1,1),message1.getDateenvoi(),"Dates différentes "+"1-1-1"+"-"+message1.getDateenvoi());

        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }
    @Test
    void create() {
        assertNotEquals(0,message.getId_message(),"id message non incrémenté");
        assertEquals("ObjetTest",message.getObjet(),"Objets différents "+"ObjetTest"+"-"+message.getObjet());
        assertEquals("ContenuTest",message.getContenu(),"Contenus différents "+"ContenuTest"+"-"+message.getContenu());
        assertEquals(new Date(2000,1,1),message.getDateenvoi(),"Dates différentes "+"1-1-1"+"-"+message.getDateenvoi());

    }
    @Test
    void update() {
        try{
            message.setObjet("ObjetTest2");
            message.setContenu("ContenuTest2");
            message = messageServiceImpl.update(message);
            assertEquals("ObjetTest2",message.getObjet(),"Objets différents "+"ObjetTest2"+"-"+message.getObjet());
            assertEquals("ContenuTest2",message.getContenu(),"Contenus différents "+"ContenuTest2"+"-"+message.getContenu());
        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }
    @Test
    void delete() {
        try{
            messageServiceImpl.delete(message);
            Assertions.assertThrows(Exception.class, () -> {
                messageServiceImpl.read(message.getId_message());
            },"message non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }
    @Test
    void all() {
        try {
            List<Message> lm = messageServiceImpl.all();
            assertNotEquals(0,lm.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les messages "+e);
        }
    }
    //On crée deux messages pour un employé et on verifie que la liste = 2
    @Test
    void getMessagesEmp() throws Exception {
        Message message1 = new Message(null,"ObjetTest2","ContenuTest2",new Date(1,1,1),null,emp);
        messageServiceImpl.create(message1);
        try {
            List<Message> lm = messageServiceImpl.getMessagesEmp(emp);
            assertEquals(2,lm.size(),"la liste ne contient pas le nombre d'élément espéré");
        }catch (Exception e){
            fail("erreur de recherche de tous les messages pour un employe "+e);
        }
    }
    @Test
    void getMessagesDate() throws Exception {
        Message message1 = new Message(null,"ObjetTest2","ContenuTest2",new Date(2000,1,1),null,emp);
        messageServiceImpl.create(message1);
        try {
            List<Message> lm = messageServiceImpl.getMessagesDate(new java.util.Date(2000,1,1));
            assertNotEquals(0,lm.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les messages "+e);
        }
    }
}

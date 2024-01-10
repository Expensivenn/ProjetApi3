package com.example.demo.webservices;
import com.example.demo.entities.Employe;
import com.example.demo.entities.Message;
import com.example.demo.services.InterfEmployeService;
import com.example.demo.services.MessageServiceImpl;
import com.example.demo.services.InterfMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/messages")
public class RestMessage {
    @Autowired
    private InterfMessageService messageServiceImpl;
    @Autowired
    private InterfEmployeService employeServiceImpl;


    //-------------------Retrouver l'employe correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> getMessage(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche le message d' id " + id);
        Message message = messageServiceImpl.read(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //-------------------Retrouver les employes portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/id_employe={id_employe}", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> listMessEmpl(@PathVariable(value="id_employe") int id) throws Exception{
        Employe emp = employeServiceImpl.read(id);
        System.out.println("recherche des message envoyé par  "+ emp.getNom());
        List<Message> messages;
        messages = messageServiceImpl.getMessagesEmp(emp);
        System.out.println(messages.get(0));
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    //-------------------Créer un message--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Message> createMessage(@RequestBody Message message) throws Exception {
        System.out.println("Création du message ");
        messageServiceImpl.create(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //-------------------Mettre à jour un message d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Message> majMessage(@PathVariable(value = "id") int id,@RequestBody Message nouvmes) throws Exception{
        System.out.println("maj du message d'id =  " + id);
        nouvmes.setId_message(id);
        Message messact = messageServiceImpl.update(nouvmes);
        return new ResponseEntity<>(messact, HttpStatus.OK);
    }

    //-------------------Effacer un employe d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMessage(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du message d'id " + id);
        Message mess = messageServiceImpl.read(id);
        messageServiceImpl.delete(mess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver tous les employe --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Message>> listMessage() throws Exception{
        System.out.println("recherche de tous les messages");
        return new ResponseEntity<>(messageServiceImpl.all(), HttpStatus.OK);
    }
    //---------------------Messages entre deux date par un employé donné--------------------------------------------
    @RequestMapping(value = "/{date1}/{date2}/{id_employe}", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> listMessDate(@PathVariable(value = "date1") String date1,
                                                      @PathVariable(value = "date2") String date2,
                                                      @PathVariable(value = "id_employe") int id) throws Exception {
        Employe emp = employeServiceImpl.read(id);
        //System.out.println("recherche des message envoyé par  "+ emp.getNom());
        Date dateToParse1;
        Date dateToParse2;
        dateToParse1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
        dateToParse2 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        List<Message> messages;
        messages = messageServiceImpl.getMessagesBetweenTwoDates(dateToParse1,dateToParse2,emp);
        //System.out.println(messages.get(0));
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    //-------------------Retrouver tous les clients triés et par page--------------------------------------------------------
    @RequestMapping(value =  "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Message>> listMess(Pageable pageable) throws Exception{
        System.out.println("recherche de tous les messages");
        return new ResponseEntity<>(messageServiceImpl.allp(pageable), HttpStatus.OK);
    }
    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }

}


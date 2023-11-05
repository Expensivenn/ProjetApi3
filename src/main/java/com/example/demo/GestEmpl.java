package com.example.demo;
import com.example.demo.entities.Employe;
import com.example.demo.entities.Message;
import com.example.demo.repositories.EmployeRepository;
import com.example.demo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/employes")
public class GestEmpl{
    @Autowired     //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    EmployeRepository employeRepository;
    @Autowired     //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    MessageRepository messageRepository;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche employes");
        List<Employe> lst;
        try {
            lst= employeRepository.findAll();
            model.put("mesEmployes", lst);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichagetousEmployes";
    }
    @RequestMapping("/selection")
    String selection(@RequestParam("numEmpl") int numEmpl, Map<String, Object> model){
        Optional<Employe> oemp = null;
        Employe emp = null;
        try {
            oemp = employeRepository.findById(numEmpl);
            if(oemp.isPresent()) emp = oemp.get();
            else throw new Exception("Employe non trouvé");
            model.put("monEmploye", emp);

        } catch (Exception e) {
            System.out.println("erreur lors de la lecture " + e);
            model.put("error",e);
            return "error";
        }
        return "affEmpl";  // page html à développer
    }
    @RequestMapping("/create")
    public String create(@RequestParam String email,@RequestParam String
            nom,@RequestParam String prenom,@RequestParam int id_bureau, Map<String, Object> model){
        System.out.println("création de client");
        Employe emp = new Employe(email,nom,prenom,id_bureau,new ArrayList<>());
        try {
            employeRepository.save(emp);//mise à jour du client avec son id par l'environnement
            System.out.println(emp);
            Collection<Employe> lcl= employeRepository.findAll();
            model.put("nouvemp",emp);
            model.put("mesEmployes", lcl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------");
                    model.put("error",e.getMessage());
            return "error";
        }
        return "nouveauEmploye";
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam int id,Map<String,Object> model){
        try {
            Employe emp;
            Optional<Employe> oemp;
            oemp = employeRepository.findById(id);
            if(oemp.isPresent()){
                emp = oemp.get();
                employeRepository.deleteById(id);
            }
            else throw new Exception("Employe non trouvé");

            Collection<Employe> lcl= employeRepository.findAll();
            model.put("emplSuppr",emp);
            model.put("mesEmployes", lcl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------");
            model.put("error",e.getMessage());
            return "error";
        }
        return "supprEmploye";
    }
    @RequestMapping("/affMessEmpl")
    public String affMessEmpl(@RequestParam int id,Map<String,Object> model){
        try {
            Employe emp;
            List<Message> lm;
            Optional<Employe> oemp;
            oemp = employeRepository.findById(id);
            if(oemp.isPresent()){
                emp = oemp.get();
                lm = messageRepository.findMessagesByEmploye(emp);
            }
            else throw new Exception("Employe non trouvé");

            //Collection<Employe> lm= employeRepository.findAll();
            model.put("emplRech",emp);
            model.put("emplMess", lm);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------");
            model.put("error",e.getMessage());
            return "error";
        }
        return "affMessEmpl";
    }


}

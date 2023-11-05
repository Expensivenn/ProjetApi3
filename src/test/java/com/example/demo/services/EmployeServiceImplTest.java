package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Message;
import com.example.demo.repositories.EmployeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeServiceImplTest {

    @Autowired
    private InterfEmployeService employeServiceImpl;

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    Employe emp;
    @BeforeEach
    void setUp() {
        try{
            emp = new Employe(null,"test@test.com","NomTest","PrenomTest",1,new ArrayList<>());
            employeServiceImpl.create(emp);
            System.out.println("création de l'employe : "+emp);
        }
        catch (Exception e){
            System.out.println("erreur de création de l'employe : "+emp +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            employeServiceImpl.delete(emp);
            System.out.println("effacement de l'employe : "+emp);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement de l'employe :"+emp+" erreur : "+e);
        }
    }

    @Test
    void read() {
        try{
            int id=emp.getId_employe();
            Employe emp2=employeServiceImpl.read(id);
            assertEquals("NomTest",emp2.getNom(),"noms différents "+"NomTest"+"-"+emp2.getNom());
            assertEquals("PrenomTest",emp2.getPrenom(),"prénoms différents "+"PrenomTest"+"-"+emp2.getPrenom());
            assertEquals("test@test.com",emp2.getEmail(),"emails différents "+"test@test.com"+"-"+emp2.getEmail());

        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }



    @Test
    void findByName() {
        List<Employe> le = employeServiceImpl.read("NomTest");
        boolean trouve=false;
        for(Employe e : le){
            if(e.getNom().startsWith("NomTest"))  trouve=true;
            else fail("un record ne correspond pas , nom = "+e.getNom());
        }
        assertTrue(trouve,"record non trouvé dans la liste");
    }

    @Test
    void create() {
        assertNotEquals(0,emp.getId_employe(),"id employe non incrémenté");
        assertEquals("NomTest",emp.getNom(),"nom employe non enregistré : "+emp.getNom()+ " au lieu de NomTest");
        assertEquals("PrenomTest",emp.getPrenom(),"prénom employe non enregistré : "+emp.getPrenom()+" au lieu de PrenomTest");
        assertEquals("test@test.com",emp.getEmail(),"email employe non enregistré : "+emp.getEmail()+" au lieu de test@test.com");
    }
    /* PAS VERIFIER DANS MA BD
    @Test()
    void creationDoublon(){   //ajouté
        Employe emp2 = new Employe(null,"test@test.com","NomTest","PrenomTest",1,new ArrayList<>());
        Assertions.assertThrows(Exception.class, () -> {
            employeServiceImpl.create(emp2);
        },"création d'un doublon");
    }
    */


    @Test
    void update() {
        try{
            emp.setNom("NomTest2");
            emp.setPrenom("PrenomTest2");
            emp = employeServiceImpl.update(emp);
            assertEquals("NomTest2",emp.getNom(),"noms différents "+"NomTest2-"+emp.getNom());
            assertEquals("PrenomTest2",emp.getPrenom(),"prénoms différents "+"PrenomTest2-"+emp.getPrenom());
        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            employeServiceImpl.delete(emp);    Assertions.assertThrows(Exception.class, () -> {
                employeServiceImpl.read(emp.getId_employe());
            },"employe non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Employe> lc = employeServiceImpl.all();
            assertNotEquals(0,lc.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les employes "+e);
        }
    }
}
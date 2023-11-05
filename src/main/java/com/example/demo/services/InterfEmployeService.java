package com.example.demo.services;

import com.example.demo.entities.Employe;
import org.springframework.stereotype.Service;

import java.util.List;
public interface InterfEmployeService extends InterfServices<Employe> {
    List<Employe> read(String nom);

    Employe read(String nom, String prenom, String email);

}

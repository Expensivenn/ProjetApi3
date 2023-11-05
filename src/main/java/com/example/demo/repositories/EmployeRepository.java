package com.example.demo.repositories;

import com.example.demo.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe,Integer> {
    public List<Employe> findByNomLike(String nom);

    public List<Employe> findByNomAndPrenomAndEmail(String nom,String prenom,String email);
}

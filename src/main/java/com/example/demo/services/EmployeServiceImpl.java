package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.repositories.EmployeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmployeServiceImpl implements InterfEmployeService {
    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public List<Employe> read(String nom) {
        return employeRepository.findByNomLike(nom + "%");
    }

    @Override
    public Employe read(String nom, String prenom, String email) {
        return employeRepository.findByNomAndPrenomAndEmail(nom, prenom, email).get(0);
    }


    @Override
    public Employe create(Employe employe) throws Exception {
        employeRepository.save(employe);
        return employe;
    }

    @Override
    public Employe read(Integer id) throws Exception {
        Optional<Employe> oemp = employeRepository.findById(id);
        return oemp.get();
    }

    @Override
    public Employe update(Employe employe) throws Exception {
        read(employe.getId_employe());
        employeRepository.save(employe);
        return employe;
    }

    @Override
    public void delete(Employe employe) throws Exception {
        employeRepository.deleteById(employe.getId_employe());
    }

    @Override
    public List<Employe> all() throws Exception {
        return employeRepository.findAll();
    }

}

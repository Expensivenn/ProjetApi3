package com.example.demo.repositories;

import com.example.demo.entities.Bureau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BureauRepository extends JpaRepository<Bureau,Integer> {
}

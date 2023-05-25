package com.projet.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.stock.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {

}

package com.projet.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.stock.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {

}

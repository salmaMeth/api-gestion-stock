package com.projet.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.stock.model.LigneCommandeFournisseur;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Integer> {

  List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Integer idCommande);

  List<LigneCommandeFournisseur> findAllByArticleId(Integer idCommande);
}

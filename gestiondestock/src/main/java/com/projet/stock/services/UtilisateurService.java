package com.projet.stock.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.stock.dto.ChangerMotDePasseUtilisateurDto;
import com.projet.stock.dto.UtilisateurDto;

@Service
public interface UtilisateurService {

  UtilisateurDto save(UtilisateurDto dto);

  UtilisateurDto findById(Integer id);

  List<UtilisateurDto> findAll();

  void delete(Integer id);

  UtilisateurDto findByEmail(String email);

  UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);


}

package com.projet.stock.services;

import java.util.List;

import com.projet.stock.dto.FournisseurDto;

public interface FournisseurService {

  FournisseurDto save(FournisseurDto dto);

  FournisseurDto findById(Integer id);

  List<FournisseurDto> findAll();

  void delete(Integer id);

}

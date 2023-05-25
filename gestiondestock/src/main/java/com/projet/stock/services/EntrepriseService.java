package com.projet.stock.services;

import java.util.List;

import com.projet.stock.dto.EntrepriseDto;

public interface EntrepriseService {

  EntrepriseDto save(EntrepriseDto dto);

  EntrepriseDto findById(Integer id);

  List<EntrepriseDto> findAll();

  void delete(Integer id);

}

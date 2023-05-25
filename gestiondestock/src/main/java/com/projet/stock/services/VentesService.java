package com.projet.stock.services;

import java.util.List;

import com.projet.stock.dto.VentesDto;

public interface VentesService {

  VentesDto save(VentesDto dto);

  VentesDto findById(Integer id);

  VentesDto findByCode(String code);

  List<VentesDto> findAll();

  void delete(Integer id);

}

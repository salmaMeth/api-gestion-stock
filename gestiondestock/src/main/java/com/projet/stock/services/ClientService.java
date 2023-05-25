package com.projet.stock.services;

import java.util.List;

import com.projet.stock.dto.ClientDto;

public interface ClientService {

  ClientDto save(ClientDto dto);

  ClientDto findById(Integer id);

  List<ClientDto> findAll();

  void delete(Integer id);

}

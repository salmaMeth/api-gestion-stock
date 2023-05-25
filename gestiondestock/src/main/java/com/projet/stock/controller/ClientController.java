package com.projet.stock.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.projet.stock.controller.api.ClientApi;
import com.projet.stock.dto.ClientDto;
import com.projet.stock.services.ClientService;

@RestController
public class ClientController implements ClientApi {

  private ClientService clientService;

  @Autowired
  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @Override
  public ClientDto save(ClientDto dto) {
    return clientService.save(dto);
  }

  @Override
  public ClientDto findById(Integer id) {
    return clientService.findById(id);
  }

  @Override
  public List<ClientDto> findAll() {
    return clientService.findAll();
  }

  @Override
  public void delete(Integer id) {
    clientService.delete(id);
  }
}

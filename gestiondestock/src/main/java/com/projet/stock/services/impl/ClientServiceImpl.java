package com.projet.stock.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.stock.dto.ClientDto;
import com.projet.stock.exception.EntityNotFoundException;
import com.projet.stock.exception.ErrorCodes;
import com.projet.stock.exception.InvalidEntityException;
import com.projet.stock.exception.InvalidOperationException;
import com.projet.stock.model.CommandeClient;
import com.projet.stock.repository.ClientRepository;
import com.projet.stock.repository.CommandeClientRepository;
import com.projet.stock.services.ClientService;
import com.projet.stock.validator.ClientValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

  private ClientRepository clientRepository;
  private CommandeClientRepository commandeClientRepository;

  @Autowired
  public ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
    this.clientRepository = clientRepository;
    this.commandeClientRepository = commandeClientRepository;
  }

  @Override
  public ClientDto save(ClientDto dto) {
    List<String> errors = ClientValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Client is not valid {}", dto);
      throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
    }

    return ClientDto.fromEntity(
        clientRepository.save(
            ClientDto.toEntity(dto)
        )
    );
  }

  @Override
  public ClientDto findById(Integer id) {
    if (id == null) {
      log.error("Client ID is null");
      return null;
    }
    return clientRepository.findById(id)
        .map(ClientDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.CLIENT_NOT_FOUND)
        );
  }

  @Override
  public List<ClientDto> findAll() {
    return clientRepository.findAll().stream()
        .map(ClientDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Client ID is null");
      return;
    }
    List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
    if (!commandeClients.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un client qui a deja des commande clients",
          ErrorCodes.CLIENT_ALREADY_IN_USE);
    }
    clientRepository.deleteById(id);
  }
}

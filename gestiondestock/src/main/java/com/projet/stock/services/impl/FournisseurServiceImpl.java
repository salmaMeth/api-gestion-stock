package com.projet.stock.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.stock.dto.FournisseurDto;
import com.projet.stock.exception.EntityNotFoundException;
import com.projet.stock.exception.ErrorCodes;
import com.projet.stock.exception.InvalidEntityException;
import com.projet.stock.exception.InvalidOperationException;
import com.projet.stock.model.CommandeClient;
import com.projet.stock.repository.CommandeFournisseurRepository;
import com.projet.stock.repository.FournisseurRepository;
import com.projet.stock.services.FournisseurService;
import com.projet.stock.validator.FournisseurValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

  private FournisseurRepository fournisseurRepository;
  private CommandeFournisseurRepository commandeFournisseurRepository;

  @Autowired
  public FournisseurServiceImpl(FournisseurRepository fournisseurRepository,
      CommandeFournisseurRepository commandeFournisseurRepository) {
    this.fournisseurRepository = fournisseurRepository;
    this.commandeFournisseurRepository = commandeFournisseurRepository;
  }

  @Override
  public FournisseurDto save(FournisseurDto dto) {
    List<String> errors = FournisseurValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Fournisseur is not valid {}", dto);
      throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
    }

    return FournisseurDto.fromEntity(
        fournisseurRepository.save(
            FournisseurDto.toEntity(dto)
        )
    );
  }

  @Override
  public FournisseurDto findById(Integer id) {
    if (id == null) {
      log.error("Fournisseur ID is null");
      return null;
    }
    return fournisseurRepository.findById(id)
        .map(FournisseurDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun fournisseur avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.FOURNISSEUR_NOT_FOUND)
        );
  }

  @Override
  public List<FournisseurDto> findAll() {
    return fournisseurRepository.findAll().stream()
        .map(FournisseurDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Fournisseur ID is null");
      return;
    }
    List<CommandeClient> commandeFournisseur = commandeFournisseurRepository.findAllByFournisseurId(id);
    if (!commandeFournisseur.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
          ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
    }
    fournisseurRepository.deleteById(id);
  }
}

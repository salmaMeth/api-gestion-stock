package com.projet.stock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.stock.dto.EntrepriseDto;
import com.projet.stock.exception.EntityNotFoundException;
import com.projet.stock.exception.ErrorCodes;
import com.projet.stock.exception.InvalidEntityException;
import com.projet.stock.repository.EntrepriseRepository;
import com.projet.stock.services.EntrepriseService;
import com.projet.stock.validator.EntrepriseValidator;

import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

  private EntrepriseRepository entrepriseRepository;


  @Autowired
  public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
    this.entrepriseRepository = entrepriseRepository;
 
  }

  @Override
  public EntrepriseDto save(EntrepriseDto dto) {
    List<String> errors = EntrepriseValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Entreprise is not valid {}", dto);
      throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
    }
    EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
        entrepriseRepository.save(EntrepriseDto.toEntity(dto))
    );
	return savedEntreprise;}



  private String generateRandomPassword() {
    return "som3R@nd0mP@$$word";
  }

  @Override
  public EntrepriseDto findById(Integer id) {
    if (id == null) {
      log.error("Entreprise ID is null");
      return null;
    }
    return entrepriseRepository.findById(id)
        .map(EntrepriseDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
  }

  @Override
  public List<EntrepriseDto> findAll() {
    return entrepriseRepository.findAll().stream()
        .map(EntrepriseDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Entreprise ID is null");
      return;
    }
    entrepriseRepository.deleteById(id);
  }
}

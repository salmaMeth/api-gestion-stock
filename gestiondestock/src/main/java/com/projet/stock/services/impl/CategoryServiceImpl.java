package com.projet.stock.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.projet.stock.dto.CategoryDto;
import com.projet.stock.exception.EntityNotFoundException;
import com.projet.stock.exception.ErrorCodes;
import com.projet.stock.exception.InvalidEntityException;
import com.projet.stock.exception.InvalidOperationException;
import com.projet.stock.model.Article;
import com.projet.stock.repository.ArticleRepository;
import com.projet.stock.repository.CategoryRepository;
import com.projet.stock.services.CategoryService;
import com.projet.stock.validator.CategoryValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

  private CategoryRepository categoryRepository;
  private ArticleRepository articleRepository;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
    this.categoryRepository = categoryRepository;
    this.articleRepository = articleRepository;
  }

  @Override
  public CategoryDto save(CategoryDto dto) {
    List<String> errors = CategoryValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Article is not valid {}", dto);
      throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
    }
    return CategoryDto.fromEntity(
        categoryRepository.save(CategoryDto.toEntity(dto))
    );
  }

  @Override
  public CategoryDto findById(Integer id) {
    if (id == null) {
      log.error("Category ID is null");
      return null;
    }
    return categoryRepository.findById(id)
        .map(CategoryDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune category avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.CATEGORY_NOT_FOUND)
        );
  }

  @Override
  public CategoryDto findByCode(String code) {
    if (!StringUtils.hasLength(code)) {
      log.error("Category CODE is null");
      return null;
    }
    return categoryRepository.findCategoryByCode(code)
        .map(CategoryDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune category avec le CODE = " + code + " n' ete trouve dans la BDD",
            ErrorCodes.CATEGORY_NOT_FOUND)
        );
  }

  @Override
  public List<CategoryDto> findAll() {
    return categoryRepository.findAll().stream()
        .map(CategoryDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Category ID is null");
      return;
    }
    List<Article> articles = articleRepository.findAllByCategoryId(id);
    if (!articles.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer cette categorie qui est deja utilise",
          ErrorCodes.CATEGORY_ALREADY_IN_USE);
    }
    categoryRepository.deleteById(id);
  }
}

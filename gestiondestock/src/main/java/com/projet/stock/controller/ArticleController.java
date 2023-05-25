package com.projet.stock.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.projet.stock.controller.api.ArticleApi;
import com.projet.stock.dto.ArticleDto;
import com.projet.stock.dto.LigneCommandeClientDto;
import com.projet.stock.dto.LigneCommandeFournisseurDto;
import com.projet.stock.dto.LigneVenteDto;
import com.projet.stock.services.ArticleService;

@RestController
public class ArticleController implements ArticleApi {

  private ArticleService articleService;

  @Autowired
  public ArticleController(
      ArticleService articleService
  ) {
    this.articleService = articleService;
  }

  @Override
  public ArticleDto save(ArticleDto dto) {
    return articleService.save(dto);
  }

  @Override
  public ArticleDto findById(Integer id) {
    return articleService.findById(id);
  }

  @Override
  public ArticleDto findByCodeArticle(String codeArticle) {
    return articleService.findByCodeArticle(codeArticle);
  }

  @Override
  public List<ArticleDto> findAll() {
    return articleService.findAll();
  }

  @Override
  public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
    return articleService.findHistoriqueVentes(idArticle);
  }

  @Override
  public List<LigneCommandeClientDto> findHistoriaueCommandeClient(Integer idArticle) {
    return articleService.findHistoriaueCommandeClient(idArticle);
  }

  @Override
  public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
    return articleService.findHistoriqueCommandeFournisseur(idArticle);
  }

  @Override
  public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
    return articleService.findAllArticleByIdCategory(idCategory);
  }

  @Override
  public void delete(Integer id) {
    articleService.delete(id);
  }
}

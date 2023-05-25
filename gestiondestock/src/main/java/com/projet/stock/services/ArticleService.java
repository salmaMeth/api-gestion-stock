package com.projet.stock.services;


import java.util.List;

import com.projet.stock.dto.ArticleDto;
import com.projet.stock.dto.LigneCommandeClientDto;
import com.projet.stock.dto.LigneCommandeFournisseurDto;
import com.projet.stock.dto.LigneVenteDto;

public interface ArticleService {

  ArticleDto save(ArticleDto dto);

  ArticleDto findById(Integer id);

  ArticleDto findByCodeArticle(String codeArticle);

  List<ArticleDto> findAll();

  List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);

  List<LigneCommandeClientDto> findHistoriaueCommandeClient(Integer idArticle);

  List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);

  List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);

  void delete(Integer id);

}

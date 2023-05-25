package com.projet.stock.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "entreprise")
public class Entreprise extends AbstractEntity {

  @Column(name = "nom")
  private String nom;

  @Column(name = "description")
  private String description;

  @Embedded
  private Adresse adresse;

  @Column(name = "codefiscal")
  private String codeFiscal;

  @Column(name = "photo")
  private String photo;

  @Column(name = "email")
  private String email;

  @Column(name = "numtel")
  private String numTel;

  @Column(name = "siteweb")
  private String steWeb;


}

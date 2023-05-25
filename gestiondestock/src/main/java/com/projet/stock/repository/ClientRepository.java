package com.projet.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.stock.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}

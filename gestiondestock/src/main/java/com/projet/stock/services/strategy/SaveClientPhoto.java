package com.projet.stock.services.strategy;


import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.flickr4java.flickr.FlickrException;
import com.projet.stock.dto.ClientDto;
import com.projet.stock.exception.ErrorCodes;
import com.projet.stock.exception.InvalidOperationException;
import com.projet.stock.services.ClientService;
import com.projet.stock.services.FlickrService;

import lombok.extern.slf4j.Slf4j;

@Service("clientStrategy")
@Slf4j
public class SaveClientPhoto implements Strategy<ClientDto> {

  private FlickrService flickrService;
  private ClientService clientService;

  @Autowired
  public SaveClientPhoto(FlickrService flickrService, ClientService clientService) {
    this.flickrService = flickrService;
    this.clientService = clientService;
  }

  @Override
  public ClientDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    ClientDto client = clientService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new InvalidOperationException("Erreur lors de l'enregistrement de photo du client", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    client.setPhoto(urlPhoto);
    return clientService.save(client);
  }
}

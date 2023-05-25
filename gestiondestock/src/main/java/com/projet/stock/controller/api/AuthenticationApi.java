package com.projet.stock.controller.api;

import static com.projet.stock.utils.Constants.AUTHENTICATION_ENDPOINT;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.projet.stock.dto.auth.AuthenticationRequest;
import com.projet.stock.dto.auth.AuthenticationResponse;

import io.swagger.annotations.Api;

@Api("authentication")
public interface AuthenticationApi {

  @PostMapping(AUTHENTICATION_ENDPOINT + "/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

}

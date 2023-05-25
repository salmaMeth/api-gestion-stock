package com.projet.stock.services.auth;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projet.stock.dto.UtilisateurDto;
import com.projet.stock.model.auth.ExtendedUser;
import com.projet.stock.services.UtilisateurService;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

  @Autowired
  private UtilisateurService service;


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UtilisateurDto utilisateur = service.findByEmail(email);

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

    return new ExtendedUser(utilisateur.getEmail(), utilisateur.getMoteDePasse(), utilisateur.getEntreprise().getId(), authorities);
  }
}

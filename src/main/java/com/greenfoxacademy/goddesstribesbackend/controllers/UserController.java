package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.*;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
import com.greenfoxacademy.goddesstribesbackend.security.jwt.JWTUtility;
import com.greenfoxacademy.goddesstribesbackend.services.KingdomService;
import com.greenfoxacademy.goddesstribesbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private UserService userService;
  private KingdomService kingdomService;

  @Autowired
  public UserController(UserService userService, KingdomService kingdomService) {
    this.userService = userService;
    this.kingdomService = kingdomService;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
    String username = registerRequestDTO.getUsername();
    String password = registerRequestDTO.getPassword();
    String kingdomName = registerRequestDTO.getKingdomName();

    if ((username == null || username.isEmpty()) && (password == null || password.isEmpty())) {
      return ResponseEntity.status(400).body(new ErrorMessage("Username and password are required."));
    }
    if (username == null || username.isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Username is required."));
    }
    if (password == null || password.isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Password is required."));
    }
    if (userService.checkUserByName(username)) {
      return ResponseEntity.status(409).body(new ErrorMessage(username + " as username is already taken."));
    }
    if (!userService.checkPassword(password)) {
      return ResponseEntity.status(400).body(new ErrorMessage("Password must be at least 8 characters."));
    }

    User newUser = userService.saveUser(username, password);
    Kingdom newKingdom = kingdomService.saveKingdom(kingdomName, newUser);
    return ResponseEntity.status(200).body(new RegisterResponseDTO(newUser.getId(), newUser.getUsername(), newKingdom.getKingdomName()));
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody UserDTO userDTO) {
    String username = userDTO.getUsername();
    String password = userDTO.getPassword();

    if ((username == null || username.isEmpty()) && (password == null || password.isEmpty())) {
      return ResponseEntity.status(400).body(new ErrorMessage("Username and password are required."));
    }
    if (username == null || username.isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Username is required."));
    }
    if (password == null || password.isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Password is required."));
    }
    if (!userService.checkUserByNameAndPassword(username, password)) {
      return ResponseEntity.status(401).body(new ErrorMessage("Username or password is incorrect."));
    }

    userService.loginUser(username);
    return ResponseEntity.status(200).body(new TokenMessage(JWTUtility.generateToken(username)));
  }

  @PostMapping("/auth")
  public ResponseEntity<Object> authenticate(@RequestBody TokenDTO tokenDTO) {
    if (tokenDTO.getToken() == null || tokenDTO.getToken().isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("No token provided."));
    }
    if (JWTUtility.parseToken(tokenDTO.getToken()) == null) {
      return ResponseEntity.status(400).body(new ErrorMessage("Invalid token."));
    }

    return ResponseEntity.status(200).body(kingdomService.createAuthenticationResponseDTO(tokenDTO));
  }

}

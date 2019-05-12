package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.ErrorMessage;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.UserAndKingdomRequestDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.UserAndKingdomResponseDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
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
  public ResponseEntity<Object> register(@RequestBody UserAndKingdomRequestDTO userAndKingdomRequestDTO){
    String username = userAndKingdomRequestDTO.getUsername();
    String password = userAndKingdomRequestDTO.getPassword();
    String kingdomname = userAndKingdomRequestDTO.getKingdomname();

    if (userService.checkMockRegisterRequest(userAndKingdomRequestDTO)) {
      return ResponseEntity.status(200).body(userService.createMockRegisterResponse());
    }
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
      return ResponseEntity.status(400).body("Username is already taken.");
    }
    if (!userService.checkPassword(password)) {
      return ResponseEntity.status(400).body("Password must be at least 8 characters.");
    }

    User newUser = userService.saveUser(username, password);
    Kingdom newKingdom = kingdomService.saveKingdom(kingdomname, newUser);
    return ResponseEntity.status(200).body(new UserAndKingdomResponseDTO(newUser.getId(), newUser.getUsername(), newKingdom.getId()));
  }

}

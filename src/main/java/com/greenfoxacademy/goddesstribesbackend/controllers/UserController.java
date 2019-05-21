package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.*;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
import com.greenfoxacademy.goddesstribesbackend.security.jwt.JWTUtility;
import com.greenfoxacademy.goddesstribesbackend.services.KingdomService;
import com.greenfoxacademy.goddesstribesbackend.services.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("https://localhost:8080")
@RestController
public class UserController {

  private UserService userService;
  private KingdomService kingdomService;

  @Autowired
  public UserController(UserService userService, KingdomService kingdomService) {
    this.userService = userService;
    this.kingdomService = kingdomService;
  }

  @ApiResponses(value = {@ApiResponse(code = 200, message ="OK", response = RegisterResponseDTO.class), @ApiResponse(code = 400, message ="Username and password are required.", response = ErrorMessage.class), @ApiResponse(code = 400, message ="Username is required.", response = ErrorMessage.class), @ApiResponse(code = 400, message ="Password is required.", response = ErrorMessage.class), @ApiResponse(code = 400, message ="Password must be at least 8 characters", response = ErrorMessage.class), @ApiResponse(code = 409, message ="Username is already taken", response = ErrorMessage.class)})
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

  @ApiResponses(value = {@ApiResponse(code = 200, message ="OK", response = TokenMessage.class), @ApiResponse(code = 400, message ="Username and password are required.", response = ErrorMessage.class), @ApiResponse(code = 400, message ="Username is required.", response = ErrorMessage.class), @ApiResponse(code = 400, message ="Password is required.", response = ErrorMessage.class), @ApiResponse(code = 401, message ="Username or password is incorrect.", response = ErrorMessage.class)})
  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginRequestDTO) {
    String username = loginRequestDTO.getUsername();
    String password = loginRequestDTO.getPassword();

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

  @ApiResponses(value = {@ApiResponse(code = 200, message ="OK", response = AuthenticationResponseDTO.class), @ApiResponse(code = 400, message ="No token provided.", response = ErrorMessage.class), @ApiResponse(code = 400, message ="Invalid token.", response = ErrorMessage.class)})
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

  @ApiResponses(value = {@ApiResponse(code = 200, message ="Logged out successfully.", response = StatusOkMessage.class), @ApiResponse(code = 400, message ="No token provided", response = ErrorMessage.class), @ApiResponse(code = 400, message ="Invalid token", response = ErrorMessage.class)})
  @PostMapping("/logout")
  public ResponseEntity<Object> mockLogout(@RequestBody TokenDTO tokenDTO) {

    if (tokenDTO.getToken() == null || tokenDTO.getToken().isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("No token provided."));
    }

    if (JWTUtility.parseToken(tokenDTO.getToken()) == null) {
      return ResponseEntity.status(400).body(new ErrorMessage("Invalid token."));
    }

    return ResponseEntity.status(200).body(new StatusOkMessage("Logged out successfully."));
  }

}

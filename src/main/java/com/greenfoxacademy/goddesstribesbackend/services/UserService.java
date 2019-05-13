package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.Mock;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.UserAndKingdomRequestDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.UserAndKingdomResponseDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
import com.greenfoxacademy.goddesstribesbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean checkMockRegisterRequest(UserAndKingdomRequestDTO userAndKingdomRequestDTO) {
    if (userAndKingdomRequestDTO == null) return false;

    String username = userAndKingdomRequestDTO.getUsername();
    String password = userAndKingdomRequestDTO.getPassword();
    String kingdomname = userAndKingdomRequestDTO.getKingdomname();

    if (username != null && password != null && kingdomname != null) {
      return username.equals(Mock.USERNAME) && password.equals(Mock.PASSWORD) && kingdomname.equals(Mock.KINGDOMNAME);
    }
    return false;
  }

  public UserAndKingdomResponseDTO createMockRegisterResponse() {
    return new UserAndKingdomResponseDTO(Mock.USERID, Mock.USERNAME, Mock.KINGDOMID);
  }

  public boolean checkUserByName(String usernameToCheck) {
    return userRepository.findUserByUsername(usernameToCheck).isPresent();
  }

  public boolean checkPassword(String passwordToCheck) {
    return passwordToCheck != null && passwordToCheck.length() >= 8;
  }

  public User saveUser(String username, String password) {
    if (!checkUserByName(username) && checkPassword(password)) {
      User newUser = new User(username, password);
      return userRepository.save(newUser);
    }
    return null;
  }

  public boolean checkUserByNameAndPassword(String usernameToCheck, String passwordToCheck){
    return userRepository.findUserByUsernameAndPassword(usernameToCheck, passwordToCheck).isPresent();
  }

  public void loginUser(String username) {
    if (checkUserByName(username)) {
      User loggidInUser = findUserByName(username).get();
      loggidInUser.setLoggedIn(true);
      userRepository.save(loggidInUser);
    }
  }

  public Optional<User> findUserByName(String username){
    return userRepository.findUserByUsername(username);
  }

}

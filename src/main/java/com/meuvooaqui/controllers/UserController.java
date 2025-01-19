package com.meuvooaqui.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuvooaqui.Domain.DTOs.PreferenceDTO;
import com.meuvooaqui.Domain.DTOs.UserDTO;
import com.meuvooaqui.services.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userService.findUserById(id);
        return userDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        UserDTO userDTO = userService.addUser(username, email, password);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserPassword(@PathVariable Long id, @RequestParam String password) {
        UserDTO userDTO = userService.updateUserPassword(id, password);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePreferenceFromUser(@PathVariable Long id, @RequestParam String airportCode) {
        userService.removePreferenceFromUser(id, airportCode);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/preferences")
    public ResponseEntity<PreferenceDTO> addPreferenceToUser(@PathVariable Long id, @RequestParam String airportCode) {
        PreferenceDTO preferenceDTO = userService.addPreferenceToUser(id, airportCode);
        return ResponseEntity.status(HttpStatus.CREATED).body(preferenceDTO);
    }

    @GetMapping("/{id}/preferences")
    public ResponseEntity<List<PreferenceDTO>> getPreferencesByUser(@PathVariable Long id) {
        List<PreferenceDTO> preferencesDTO = userService.getPreferencesByUser(id);
        return ResponseEntity.ok(preferencesDTO);
    }
}

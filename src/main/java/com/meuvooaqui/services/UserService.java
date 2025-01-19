package com.meuvooaqui.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meuvooaqui.Domain.DTOs.PreferenceDTO;
import com.meuvooaqui.Domain.DTOs.UserDTO;
import com.meuvooaqui.Domain.models.Preference;
import com.meuvooaqui.Domain.models.User;
import com.meuvooaqui.repositories.PreferenceRepository;
import com.meuvooaqui.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PreferenceRepository preferenceRepository;

    public UserService(UserRepository userRepository, PreferenceRepository preferenceRepository) {
        this.userRepository = userRepository;
        this.preferenceRepository = preferenceRepository;
    }

    @Transactional
    public UserDTO addUser(String username, String email, String password) {
        User user = new User(username, email, password);
        userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDTO::new)
                .toList();
    }

    @Transactional
    public Optional<UserDTO> findUserById(Long id) {
        return userRepository.findById(id).map(UserDTO::new);
    }

    @Transactional
    public UserDTO updateUserPassword(Long id, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        user.setPassword(password);
        userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public PreferenceDTO addPreferenceToUser(Long id, String airportCode) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Preference preference = new Preference(airportCode, user);
        preferenceRepository.save(preference);
        PreferenceDTO preferenceDTO = new PreferenceDTO(preference);
        return preferenceDTO;
    }

    @Transactional
    public List<PreferenceDTO> getPreferencesByUser(Long userId) {
        List<Preference> preferences = preferenceRepository.findByUserId(userId);
        return preferences.stream().map(p -> new PreferenceDTO(p)).toList();
    }

    @Transactional
    public void removePreferenceFromUser(Long userId, String airportCode) {
        Preference preference = preferenceRepository.findByUserIdAndAirportCode(userId, airportCode)
                .orElseThrow(() -> new RuntimeException("Preference not found"));
        preferenceRepository.delete(preference);
    }
}

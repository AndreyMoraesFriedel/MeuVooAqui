package com.meuvooaqui.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meuvooaqui.models.Preference;
import com.meuvooaqui.models.User;
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
    public User addUser(String username, String email, String password){
        User user = new User(username, email, password);
        return userRepository.save(user);
    }
    
    @Transactional
    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public User updateUser(User user){
        return userRepository.save(user);
    }

    @Transactional
    public Preference addPreferenceToUser(Long id, String airportCode){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Preference preference = new Preference(airportCode, user);
        return preferenceRepository.save(preference);
    }

    @Transactional
    public List<Preference> getPreferencesByUser(Long userId) {
        return preferenceRepository.findByUserId(userId);
    }

    @Transactional
    public void removePreferenceFromUser(Long userId, String airportCode) {
        Preference preference = preferenceRepository.findByUserIdAndAirportCode(userId, airportCode)
                .orElseThrow(() -> new RuntimeException("Preference not found"));
        preferenceRepository.delete(preference);
    }
}
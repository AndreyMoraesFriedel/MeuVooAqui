package com.meuvooaqui;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.meuvooaqui.models.Flight;
import com.meuvooaqui.models.Notification;
import com.meuvooaqui.models.Preference;
import com.meuvooaqui.models.User;
import com.meuvooaqui.repositories.FlightRepository;
import com.meuvooaqui.repositories.NotificationRepository;
import com.meuvooaqui.repositories.PreferenceRepository;
import com.meuvooaqui.repositories.UserRepository;

@Configuration
@Component
public class DataLoader {
    
    private final UserRepository userRepository;
    private final PreferenceRepository preferenceRepository;
    private final FlightRepository flightRepository;
    private final NotificationRepository notificationRepository;
    private final boolean enableDataLoader = false;
    
    public DataLoader(UserRepository userRepository, PreferenceRepository preferenceRepository,
            FlightRepository flightRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.preferenceRepository = preferenceRepository;
        this.flightRepository = flightRepository;
        this.notificationRepository = notificationRepository;
    }

    @Bean
    public ApplicationRunner loadData(){
        return args -> {
            if(enableDataLoader){
                User user1 = new User("Andrey", "andrey@gmail.com", "123");
                userRepository.save(user1);
    
                Preference preferenciaDoUsuario1 = new Preference("SBNF", user1);
                preferenceRepository.save(preferenciaDoUsuario1);
    
                Flight vooDeNavegantesParaSaoPaulo = new Flight("GLO1733", "SBNF", "SBSP", "ATRASADO", null, null);
                flightRepository.save(vooDeNavegantesParaSaoPaulo);
    
                Notification notificacaoDoUsuario1 = new Notification("VOO ATRASADO", null, user1);
                notificationRepository.save(notificacaoDoUsuario1);
    
                System.out.println("ALL OF USERS: ");
                userRepository.findAll().forEach(System.out::println);
    
                System.out.println("ALL OF USERS PREFERENCES: ");
                preferenceRepository.findAll().forEach(System.out::println);
    
                System.out.println("ALL OF FLIGHTS: ");
                flightRepository.findAll().forEach(System.out::println);
    
                System.out.println("ALL OF USERS NOTIFICATIONS: ");
                notificationRepository.findAll().forEach(System.out::println);
            }
        };
    }    

}

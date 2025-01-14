package com.meuvooaqui;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.meuvooaqui.models.Flight;
import com.meuvooaqui.models.Notification;
import com.meuvooaqui.models.Preference;
import com.meuvooaqui.models.User;
import com.meuvooaqui.models.UserFlight;
import com.meuvooaqui.repositories.FlightRepository;
import com.meuvooaqui.repositories.NotificationRepository;
import com.meuvooaqui.repositories.PreferenceRepository;
import com.meuvooaqui.repositories.UserFlightRepository;
import com.meuvooaqui.repositories.UserRepository;

@Configuration
@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final PreferenceRepository preferenceRepository;
    private final FlightRepository flightRepository;
    private final NotificationRepository notificationRepository;
    private final UserFlightRepository userFlightRepository;
    private final boolean enableDataLoader = false;

    public DataLoader(
            UserRepository userRepository,
            PreferenceRepository preferenceRepository,
            FlightRepository flightRepository,
            NotificationRepository notificationRepository,
            UserFlightRepository userFlightRepository) {
        this.userRepository = userRepository;
        this.preferenceRepository = preferenceRepository;
        this.flightRepository = flightRepository;
        this.notificationRepository = notificationRepository;
        this.userFlightRepository = userFlightRepository;
    }

    @Bean
    public ApplicationRunner loadData() {
        return args -> {
            if (enableDataLoader) {
                // Criando e salvando usuários
                User user1 = new User("Andrey", "andrey@gmail.com", "123");
                userRepository.save(user1);

                // Criando e salvando preferências
                Preference preference1 = new Preference("SBNF", user1);
                preferenceRepository.save(preference1);

                // Criando e salvando voos
                String scheduleDeparture1 = "2025-01-15T14:00:00";
                String scheduleArrival1 = "2025-01-15T17:00:00";

                Flight flight1 = new Flight("GLO1733", "SBNF", "SBSP", "VOO PROGRAMADO", scheduleDeparture1, scheduleArrival1);
                flightRepository.save(flight1);

                // Associando o usuário ao voo (monitoramento)
                UserFlight userFlight1 = new UserFlight(user1, flight1);
                userFlightRepository.save(userFlight1);

                // Criando e salvando notificações com base no UserFlight
                String timestamp1 = "2025-01-15T14:30:00";
                flight1.setStatus("ATRASADO");
                flightRepository.save(flight1);
                Notification notification1 = new Notification("VOO ATRASADO", timestamp1, userFlight1);
                notificationRepository.save(notification1);

                // Debug: Imprimindo dados salvos
                System.out.println("ALL USERS: ");
                userRepository.findAll().forEach(System.out::println);

                System.out.println("ALL USER PREFERENCES: ");
                preferenceRepository.findAll().forEach(System.out::println);

                System.out.println("ALL FLIGHTS: ");
                flightRepository.findAll().forEach(System.out::println);

                System.out.println("ALL USER-FLIGHT RELATIONSHIPS: ");
                userFlightRepository.findAll().forEach(System.out::println);

                System.out.println("ALL NOTIFICATIONS: ");
                notificationRepository.findAll().forEach(System.out::println);
            }
        };
    }
}

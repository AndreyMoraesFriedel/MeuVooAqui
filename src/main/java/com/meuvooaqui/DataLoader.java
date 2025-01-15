package com.meuvooaqui;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.meuvooaqui.models.Flight;
import com.meuvooaqui.models.User;
import com.meuvooaqui.models.UserFlight;
import com.meuvooaqui.services.FlightService;
import com.meuvooaqui.services.NotificationService;
import com.meuvooaqui.services.UserFlightService;
import com.meuvooaqui.services.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@Component
public class DataLoader {

    private final UserService userService;
    private final FlightService flightService;
    private final NotificationService notificationService;
    private final UserFlightService userFlightService;
    private final boolean enableDataLoader = false;

    public DataLoader(
            UserService userService,
            FlightService flightService,
            NotificationService notificationService,
            UserFlightService userFlightService) {
        this.userService = userService;
        this.flightService = flightService;
        this.notificationService = notificationService;
        this.userFlightService = userFlightService;
    }

    @Bean
    public ApplicationRunner loadData() {
        return args -> {
            if (enableDataLoader) {
                // Configurando o formatador para converter Strings em LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

                // Criando e salvando usuários
                User user1 = userService.addUser("Andrey", "andrey@gmail.com", "123");

                // Criando e salvando preferências
                userService.addPreferenceToUser(user1.getId(), "SBNF");

                // Criando e salvando voos
                LocalDateTime scheduleDeparture1 = LocalDateTime.parse("2025-01-15T14:00:00", formatter);
                LocalDateTime scheduleArrival1 = LocalDateTime.parse("2025-01-15T17:00:00", formatter);

                Flight flight1 = flightService.addFlight(
                        "GLO1733",
                        "SBNF",
                        "SBSP",
                        "VOO PROGRAMADO",
                        scheduleDeparture1,
                        scheduleArrival1
                );

                // Associando o usuário ao voo (monitoramento)
                UserFlight userFlight1 = userFlightService.addFlightToUser(user1, flight1);

                // Criando e salvando notificações com base no UserFlight
                flightService.updateFlightStatus(flight1.getId(), "ATRASADO");

                LocalDateTime timestamp1 = LocalDateTime.parse("2025-01-15T14:30:00", formatter);
                notificationService.addFlightNotification("VOO ATRASADO", timestamp1, userFlight1);

                // Debug: Imprimindo dados salvos
                System.out.println("ALL USERS: ");
                userService.findUserById(user1.getId()).ifPresent(System.out::println);

                System.out.println("ALL USER PREFERENCES: ");
                userService.getPreferencesByUser(user1.getId()).forEach(System.out::println);

                System.out.println("ALL FLIGHTS: ");
                flightService.findFlightsByAirport("SBNF").forEach(System.out::println);

                System.out.println("ALL USER-FLIGHT RELATIONSHIPS: ");
                userFlightService.getFlightsByUser(user1.getId()).forEach(System.out::println);

                System.out.println("ALL NOTIFICATIONS: ");
                notificationService.getNotificationsByUser(user1.getId()).forEach(System.out::println);
            }
        };
    }
}
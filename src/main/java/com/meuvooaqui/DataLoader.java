package com.meuvooaqui;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.meuvooaqui.Domain.DTOs.FlightDTO;
import com.meuvooaqui.Domain.DTOs.UserDTO;
import com.meuvooaqui.Domain.DTOs.UserFlightDTO;
import com.meuvooaqui.Domain.models.Flight;
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
    private final boolean enableDataLoader = true; 

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
                // Formatador de datas
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

                System.out.println("==== Iniciando DataLoader ====");

                // Criando usuários
                System.out.println("Criando usuários...");
                UserDTO user1 = userService.addUser("Andrey", "andrey@gmail.com", "123");
                UserDTO user2 = userService.addUser("Maria", "maria@gmail.com", "123");

                // Adicionando preferências aos usuários
                System.out.println("Adicionando preferências aos usuários...");
                userService.addPreferenceToUser(user1.getId(), "SBNF");
                userService.addPreferenceToUser(user2.getId(), "SBSP");

                // Criando voos
                System.out.println("Criando voos...");
                LocalDateTime scheduleDeparture1 = LocalDateTime.parse("2025-01-15T14:00:00", formatter);
                LocalDateTime scheduleArrival1 = LocalDateTime.parse("2025-01-15T17:00:00", formatter);

                LocalDateTime scheduleDeparture2 = LocalDateTime.parse("2025-01-16T08:00:00", formatter);
                LocalDateTime scheduleArrival2 = LocalDateTime.parse("2025-01-16T10:00:00", formatter);

                FlightDTO flight1 = flightService.addFlight(new FlightDTO(new Flight(
                        "GLO1733",
                        "SBNF",
                        "SBSP",
                        "VOO PROGRAMADO",
                        scheduleDeparture1,
                        scheduleArrival1
                )));

                FlightDTO flight2 = flightService.addFlight(new FlightDTO(new Flight(
                        "LAT1234",
                        "SBSP",
                        "SBGL",
                        "VOO PROGRAMADO",
                        scheduleDeparture2,
                        scheduleArrival2
                )));

                // Associando usuários aos voos
                System.out.println("Associando usuários aos voos...");
                UserFlightDTO userFlight1 = userFlightService.addFlightToUser(user1.getId(), flight1.getId());
                UserFlightDTO userFlight2 = userFlightService.addFlightToUser(user2.getId(), flight2.getId());

                // Atualizando status de voos e criando notificações
                System.out.println("Atualizando status de voos...");
                flightService.updateFlightStatus(flight1.getId(), "ATRASADO");
                flightService.updateFlightStatus(flight2.getId(), "CANCELADO");

                System.out.println("Criando notificações...");
                LocalDateTime timestamp1 = LocalDateTime.parse("2025-01-15T14:30:00", formatter);
                notificationService.addFlightNotification("Seu voo foi atrasado", timestamp1, userFlight1.getId());

                LocalDateTime timestamp2 = LocalDateTime.parse("2025-01-16T07:00:00", formatter);
                notificationService.addFlightNotification("Seu voo foi cancelado", timestamp2, userFlight2.getId());

                // Debug: Imprimindo os dados salvos
                System.out.println("\n==== Debug: Dados Iniciais ====");
                System.out.println("Usuários cadastrados:");
                System.out.println(user1);
                System.out.println(user2);

                System.out.println("\nPreferências dos usuários:");
                userService.getPreferencesByUser(user1.getId()).forEach(System.out::println);
                userService.getPreferencesByUser(user2.getId()).forEach(System.out::println);

                System.out.println("\nVoos cadastrados:");
                flightService.findFlightsByAirport("SBNF").forEach(System.out::println);
                flightService.findFlightsByAirport("SBSP").forEach(System.out::println);

                System.out.println("\nAssociações usuário-voo:");
                userFlightService.getFlightsByUser(user1.getId()).forEach(System.out::println);
                userFlightService.getFlightsByUser(user2.getId()).forEach(System.out::println);

                System.out.println("\nNotificações criadas:");
                notificationService.getNotificationsByUser(user1.getId()).forEach(System.out::println);
                notificationService.getNotificationsByUser(user2.getId()).forEach(System.out::println);

                System.out.println("==== DataLoader Finalizado ====");
            }
        };
    }
}

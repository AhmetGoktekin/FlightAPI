package com.company.FlightAPI.dao;
import com.company.FlightAPI.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {

    List<Flight> findByDepartureAirportAndDestinationAirportAndDepartureDateTimeBetweenAndReturnDateTimeBetween(
            String departureAirport, String destinationAirport, LocalDateTime departureStartOfDay,
            LocalDateTime departureEndOfDay, LocalDateTime returnStartOfDay,
            LocalDateTime returnEndOfDay);
    List<Flight> findByDepartureAirportAndDestinationAirportAndDepartureDateTimeBetweenAndReturnDateTime(
            String departureAirport, String destinationAirport, LocalDateTime departureStartOfDay,
            LocalDateTime departureEndOfDay, LocalDate returnDate);
}

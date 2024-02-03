package com.company.FlightAPI.service;

import com.company.FlightAPI.dao.FlightRepository;
import com.company.FlightAPI.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository theFlightRepository) {
        this.flightRepository = theFlightRepository;
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Flight findById(int id) {
        Optional<Flight> result = flightRepository.findById(id);
        Flight theFlight = null;

        if(result.isPresent()) {
            theFlight=result.get();
        }
        else{
            throw new RuntimeException("No Flight with id: "+id);
        }
        return theFlight;
    }

    public Flight save(Flight theFlight) {
        return flightRepository.save(theFlight);
    }

    public void deleteById(int id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> searchAPI
            (String departureAirport,
            String destinationAirport,
             LocalDate  departureDate,
             LocalDate returnDate) {

        LocalDateTime departureStartOfDay = departureDate.atStartOfDay();
        LocalDateTime departureEndOfDay = departureDate.atTime(LocalTime.MAX);


        if (returnDate==null){ // If return date is not provided then it's a ONE-WAY Flight
            List<Flight> oneWay = flightRepository.findByDepartureAirportAndDestinationAirportAndDepartureDateTimeBetweenAndReturnDateTime(
                    departureAirport,  destinationAirport,  departureStartOfDay, departureEndOfDay,null);
            return oneWay;
        }
        else{//then it's a 2-WAY Flight but returnDate must be after than departureDate or equal
            if ( departureDate.isBefore(returnDate) || departureDate.isEqual(returnDate) ){
                ArrayList<Flight> allFlights = new ArrayList<Flight>();

                LocalDateTime returnStartOfDay = returnDate.atStartOfDay();
                LocalDateTime returnEndOfDay = returnDate.atTime(LocalTime.MAX);


                List<Flight> firstFlight  = flightRepository.findByDepartureAirportAndDestinationAirportAndDepartureDateTimeBetweenAndReturnDateTimeBetween(
                        departureAirport,  destinationAirport,  departureStartOfDay, departureEndOfDay, returnStartOfDay,returnEndOfDay);
                allFlights.addAll(firstFlight);

                if (!departureDate.isEqual(returnDate)){// prevent the 2nd flight with same departure and return date as 2nd flight
                List<Flight> secondFlight = flightRepository.findByDepartureAirportAndDestinationAirportAndDepartureDateTimeBetweenAndReturnDateTime(
                        destinationAirport, departureAirport,   returnStartOfDay, returnEndOfDay,null);
                allFlights.addAll(secondFlight);}

                return allFlights;
            }
            return null;
        }

    }

}

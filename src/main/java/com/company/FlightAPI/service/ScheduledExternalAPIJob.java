package com.company.FlightAPI.service;

import com.company.FlightAPI.dao.AirportRepository;
import com.company.FlightAPI.dao.FlightRepository;
import com.company.FlightAPI.entity.Airport;
import com.company.FlightAPI.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ScheduledExternalAPIJob {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepository;

    // Scheduled to work at 01:00 every day.
    @Scheduled(cron = "0 0 1 * * ?")
    public void fetchAndSaveFlightsFromExternalApi() {
        List<Airport> airports = airportRepository.findAll();
        List<Flight> externalFlightData = new ArrayList<>();

        int counter=0;
        while(counter<=5) {

            Airport departureAirport = getRandomAirport(airports);
            Airport returnAirport = getRandomAirport(airports);

            if (departureAirport!=returnAirport){//2 way flight
                // 1st Flight
                Flight outboundFlight = new Flight();
                outboundFlight.setDepartureAirport(departureAirport.getCity());
                outboundFlight.setDestinationAirport(returnAirport.getCity());
                outboundFlight.setDepartureDateTime(LocalDateTime.now().plusHours(counter));
                outboundFlight.setReturnDateTime(LocalDateTime.now().plusDays(+1).plusHours(new Random().nextInt(10) + 1 + counter));
                outboundFlight.setPrice((new Random().nextInt(900) + 100) + 0.99);
                externalFlightData.add(outboundFlight);

                // 2nd Flight
                Flight returnFlight = new Flight();
                returnFlight.setDepartureAirport(outboundFlight.getDestinationAirport());
                returnFlight.setDestinationAirport(outboundFlight.getDepartureAirport());
                returnFlight.setDepartureDateTime(outboundFlight.getReturnDateTime());
                returnFlight.setReturnDateTime(null);
                returnFlight.setPrice(outboundFlight.getPrice());
                externalFlightData.add(returnFlight);
                counter++;
            }
        }

        flightRepository.saveAll(externalFlightData);
    }

    private Airport getRandomAirport(List<Airport> airports) {
        Random random = new Random();
        return airports.get(random.nextInt(airports.size()));
    }
    

}

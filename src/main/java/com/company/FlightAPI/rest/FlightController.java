package com.company.FlightAPI.rest;

import com.company.FlightAPI.entity.Flight;
import com.company.FlightAPI.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightService flightService;

    //Get All Flights
    @GetMapping("/flights")
    public List<Flight> findAll (){
        return flightService.findAll();
    }

    //Get Flight By ID
    @GetMapping("/flights/{flightId}")
    public Flight getFlightByID(@PathVariable int flightId){
        Flight flight = flightService.findById(flightId);

        if (flight==null){
            throw new RuntimeException("No Flight with this ID: " + flightId);
        }
        return flight;
    }

    //Create New Flight
    @PostMapping("/flights")
    public Flight newFlight(@RequestBody Flight theFlight){
        Flight flight = flightService.save(theFlight);
        return flight;
    }

    //Update Flight By ID
    @PutMapping("/flights/{flightId}")
    public Flight updateFlight(@PathVariable int flightId,
                               @RequestBody Flight theFlight){

        Flight flight = flightService.findById(flightId);

        if (flight==null){
            throw new RuntimeException("No Flight with this ID: " + flightId);
        }

        flight.setPrice(theFlight.getPrice());
        flight.setDestinationAirport(theFlight.getDestinationAirport());
        flight.setDepartureAirport(theFlight.getDepartureAirport());
        flight.setReturnDateTime(theFlight.getReturnDateTime());
        flight.setDepartureDateTime(theFlight.getDepartureDateTime());
        return flightService.save(flight);
    }


    //Delete Flight By ID
    @DeleteMapping("/flights/{flightId}")
    public String deleteFlight(@PathVariable int flightId){
        Flight flight = flightService.findById(flightId);

        if (flight==null){
            throw new RuntimeException("No Flight with this ID: " + flightId);
        }

        flightService.deleteById(flightId);
        return "Flight deleted with id: " + flightId;
    }



    //Search Flights by departureAirport, arrivalAirport, departureDateTime, returnDateTime
    @GetMapping("/search")
    public List<Flight> searchAPI(
            @RequestParam String departureAirport,
            @RequestParam String destinationAirport,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate departureDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate returnDate) {

        List<Flight> flight = flightService.searchAPI(
                 departureAirport,
                destinationAirport,
                departureDate,
                returnDate);

        if (flight==null){
            throw new RuntimeException("No flight with the provided parameters.");
        }
        return flight;
    }

}

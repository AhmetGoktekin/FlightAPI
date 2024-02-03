package com.company.FlightAPI.rest;

import com.company.FlightAPI.entity.Airport;
import com.company.FlightAPI.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirportController {

    @Autowired
    private AirportService airportService;

    //Get All Airports
    @GetMapping("/airports")
    public List<Airport> findAll (){
        return airportService.findAll();
    }

    //Get Airport By ID
    @GetMapping("/airports/{airportId}")
    public Airport getAirportByID(@PathVariable int airportId){
        Airport airport = airportService.findById(airportId);

        if (airport==null){
            throw new RuntimeException("No Airport with this ID: " + airportId);
        }
        return airport;
    }

    //Create New Airport
    @PostMapping("/airports")
    public Airport newAirport(@RequestBody Airport theAirport){
        Airport airport = airportService.save(theAirport);
        return airport;
    }

    //Update Airport By ID
    @PutMapping("/airports/{airportId}")
    public Airport updateAirport(@PathVariable int airportId,
                               @RequestBody Airport theAirport){

        Airport airport = airportService.findById(airportId);

        if (airport==null){
            throw new RuntimeException("No Airport with this ID: " + airportId);
        }
        airport.setCity(theAirport.getCity());
        return airportService.save(airport);
    }


    //Delete Airport By ID
    @DeleteMapping("/airports/{airportId}")
    public String deleteAirport(@PathVariable int airportId){
        Airport airport = airportService.findById(airportId);

        if (airport==null){
            throw new RuntimeException("No Airport with this ID: " + airportId);
        }

        airportService.deleteById(airportId);
        return "Airport deleted with id: " + airportId;
    }

}

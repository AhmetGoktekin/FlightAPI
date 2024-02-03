package com.company.FlightAPI.service;

import com.company.FlightAPI.dao.AirportRepository;
import com.company.FlightAPI.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository flightRepository;

    @Autowired
    public AirportService(AirportRepository theAirportRepository) {
        this.flightRepository = theAirportRepository;
    }

    public List<Airport> findAll() {
        return flightRepository.findAll();
    }

    public Airport findById(int id) {
        Optional<Airport> result = flightRepository.findById(id);
        Airport theAirport = null;

        if(result.isPresent()) {
            theAirport=result.get();
        }
        else{
            throw new RuntimeException("No Airport with id: "+id);
        }
        return theAirport;
    }

    public Airport save(Airport theAirport) {
        return flightRepository.save(theAirport);
    }

    public void deleteById(int id) {
        flightRepository.deleteById(id);
    }
}

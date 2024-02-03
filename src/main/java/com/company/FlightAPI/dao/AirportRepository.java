package com.company.FlightAPI.dao;
import com.company.FlightAPI.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Integer> {

}

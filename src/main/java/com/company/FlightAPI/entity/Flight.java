package com.company.FlightAPI.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Flights")
public class Flight {

    //define fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FlightID")
    private Long flightID;

    @Column(name = "DepartureAirport")
    private String departureAirport;

    @Column(name = "DestinationAirport")
    private String destinationAirport;

    @Column(name = "DepartureDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime departureDateTime;

    @Column(name = "ReturnDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returnDateTime;

    @Column(name = "Price")
    private Double price;

    // Getter and Setter methods
    public Long getFlightID() {
        return flightID;
    }

    public void setFlightID(Long flightID) {
        this.flightID = flightID;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}

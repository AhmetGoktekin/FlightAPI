package com.company.FlightAPI.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Airports")
public class Airport {

    //define fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AirportID")
    private Long airportID;

    @Column(name = "City")
    private String city;

    // Getter and Setter methods
    public Long getAirportID() {
        return airportID;
    }

    public void setAirportID(Long airportID) {
        this.airportID = airportID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

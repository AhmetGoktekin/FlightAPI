# Flight Search API

This API project constitutes a resilient Flight Search API, allowing users to inquire, display, and conduct searches for flight details using designated criteria. 

## Getting Started

### Installation

1. **Clone the Project:**
   ```bash
   git clone https://github.com/AhmetGoktekin/FlightAPI.git

2. **Go to the Project Folder:**
   ```bash
   cd FlightAPI
   
### Execution of the Application

1. **Run the Application:**
   - On Mac and Linux
   ```bash
   ./mvnw spring-boot:run
   ```
   - On Windows:
   ```bash
   mvnw spring-boot:run
   ```

### Access Credentials
- Username: `user`
- Password: `password` (Note: this can be changed from the 'application.properties')

Use the provided credentials above for HTTP Basic Authentication when accessing the Swagger UI to interact with the API endpoints.

## API Endpoints
The program provides different endpoints that you can access through the Swagger UI. Below are the specifics for the flight search features:

- `GET /flights`: Display all flights.
- `GET /flights/{flightId}`: Get details of a flight using its ID.
- `POST /flights`: Add a new flight.
- `PUT /flights/{flightId}`: Modify an existing flight using its ID.
- `DELETE /flights/{flightId}`: Remove a flight using its ID.


- `GET /airports`: Display all airports.
- `GET /airports/{airportId}`: Get details of an airport using its ID.
- `POST /airports`: Add a new airport.
- `PUT /airports/{airportId}`: Modify an existing airport using its ID.
- `DELETE /airports/{airportId}`: Remove an airport using its ID.

## Database

Note that, the SQL script below has to be run for MySQL RDBMS in order to create the
required database.

```bash 
DatabaseCreateQuery.sql
``` 

For further database configuration please modify 'application.properties'.

### Flight Search Endpoint

**Search Flights by One-Way and Round-Trip:**

`GET /flights/search` This endpoint handles searches for both one-way and round-trip flights. Depending on whether a return time is provided, it can display either one-way or round-trip flight options.
- For one-way flights, provide departureAirport, destinationAirport, and departureTime as inputs. The response will include flights that match these criteria.
- For round-trip flights, add returnTime as additional input. The response will include outbound and round-trip flights that meet the specified criteria.
- Parameters: `departureAirport`, `destinationAirport`, `departureTime`, `returnTime` (optional).

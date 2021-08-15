# Yieldbroker Sport Centre Reservation Application
This is a coding test from Yieldbroker.

## Assumptions:
- Changes to reservation is NOT allowed after reservation is created
- Each court has only one session per day and can be reserved by 4 players in the session
- Each court can be booked multi-times (no greater than 4) by the same player in the session 
- Player can reserve on the desired date but cannot reserve a particular court 

## Prerequisites
- JDK 11
- Maven
- Port number 8888

### How To Build
Use the below maven command to generate the artifact.
```
  mvn clean install
```

### How To Run
Use the below command to run the application.
```
  java -jar target/sport-centre-reservation-1.0.0-SNAPSHOT.jar
```

#### Implemented RESTful API
The following endpoints are implemented.
- /reservations
- /reservations/create?date=${reservationDate}&playerName=${plerName}


| Sample Endpoint Interaction				                                  |	Http Method	  | Comment                    					    |
|:----------------------------------------------------------------------------|:--------------|:------------------------------------------------|
| http://localhost:8888/reservations		                                  |	   GET	      | get all reservations							|
| http://localhost:8888/reservations/create?date=21-09-2021&playerName=Hugo   |    POST       | create a reservation for Hugo on 21 Sep 2021	|



## Authors

* **Jun Feng** 
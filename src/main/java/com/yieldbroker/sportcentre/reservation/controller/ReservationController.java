package com.yieldbroker.sportcentre.reservation.controller;

import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import com.yieldbroker.sportcentre.reservation.repository.service.ReservationOrchestrator;
import java.util.Date;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

  private final ReservationOrchestrator reservationOrchestrator;

  @PostMapping("/create")
  public Reservation createReservation(@RequestParam("date") Date date, @RequestParam("playerName") String playerName) {
    System.out.println("endpoint is hit");

    Objects.requireNonNull(date, "date can't be null");
    Objects.requireNonNull(playerName, "playerName can't be null");

    Reservation reservation = reservationOrchestrator.createReservation(date, playerName);
    if (reservation == null) {
      throw new RuntimeException("can't create reservation");
    }
    return reservation;
  }

}

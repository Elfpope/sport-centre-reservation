package com.yieldbroker.sportcentre.reservation.controller;

import com.yieldbroker.sportcentre.reservation.dto.ReservationDto;
import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import com.yieldbroker.sportcentre.reservation.exception.BookoutException;
import com.yieldbroker.sportcentre.reservation.repository.service.ReservationOrchestrator;
import com.yieldbroker.sportcentre.reservation.util.DtoUtils;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

  private final ReservationOrchestrator reservationOrchestrator;

  @GetMapping
  public List<ReservationDto> getReservations() {
    return reservationOrchestrator.getReservations()
        .stream()
        .map(DtoUtils::toReservationDto)
        .collect(Collectors.toList());
  }

  @PostMapping("/create")
  public ReservationDto createReservation(@RequestParam("date") Date date, @RequestParam("playerName") String playerName) {
    Objects.requireNonNull(date, "date can't be null");
    Objects.requireNonNull(playerName, "playerName can't be null");

    Reservation reservation = reservationOrchestrator.createReservation(date, playerName);
    if (reservation == null) {
      throw new BookoutException();
    }
    return DtoUtils.toReservationDto(reservation);
  }

}

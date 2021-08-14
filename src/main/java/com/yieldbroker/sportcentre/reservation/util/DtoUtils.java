package com.yieldbroker.sportcentre.reservation.util;

import com.yieldbroker.sportcentre.reservation.dto.ReservationDto;
import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import java.util.Objects;

public class DtoUtils {

  private DtoUtils () {
  }

  public static ReservationDto toReservationDto(Reservation reservation) {
    Objects.requireNonNull(reservation, "reservation can't be null");
    return new ReservationDto(
        reservation.getDate(),
        reservation.getTennisCourt().getName(),
        reservation.getPlayer().getName()
    );
  }
}

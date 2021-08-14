package com.yieldbroker.sportcentre.reservation.dto;

import java.util.Date;
import lombok.Data;

@Data
public class ReservationDto {
  private final Date date;

  private final String tennisCourt;

  private final String player;
}

package com.yieldbroker.sportcentre.reservation.repository.service;

import com.yieldbroker.sportcentre.reservation.entity.Player;
import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import com.yieldbroker.sportcentre.reservation.entity.TennisCourt;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationOrchestrator {
  private final PlayerService playerService;
  private final TennisCourtService tennisCourtService;
  private final ReservationService reservationService;

  public Reservation createReservation(Date reservationDate, String playerName) {
    List<TennisCourt> tennisCourts = tennisCourtService.getTennisCourts();
    for (TennisCourt court : tennisCourts){
      reservationService.isReservable(reservationDate, court);
    }

    Player player = new Player();
    player.setName(playerName);

    playerService.createPlayer(player);
    return reservationService.createReservation(reservationDate, tennisCourts.get(0), player);
  }

}

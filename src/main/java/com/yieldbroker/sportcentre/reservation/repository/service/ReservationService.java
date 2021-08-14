package com.yieldbroker.sportcentre.reservation.repository.service;

import com.yieldbroker.sportcentre.reservation.entity.Player;
import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import com.yieldbroker.sportcentre.reservation.entity.TennisCourt;
import com.yieldbroker.sportcentre.reservation.repository.ReservationRepository;
import com.yieldbroker.sportcentre.reservation.util.Constants;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;

  public List<Reservation> getReservations() {
    return reservationRepository.findAll();
  }

  public boolean isReservable(Date reservationDate, TennisCourt tennisCourt) {
    List<Reservation> reservations = reservationRepository.findAllByDateAndCourt(reservationDate, tennisCourt.getId());
    return reservations.size() < Constants.MAX_PLAYER_NUMBER_PER_COURT;
  }

  public Reservation createReservation(Date reservationDate, TennisCourt tennisCourt, Player player) {
    Reservation reservation = new Reservation();
    reservation.setDate(reservationDate);
    reservation.setTennisCourt(tennisCourt);
    reservation.setPlayer(player);
    return reservationRepository.save(reservation);
  }

}

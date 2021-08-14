package com.yieldbroker.sportcentre.reservation.repository.service;

import com.yieldbroker.sportcentre.reservation.entity.Player;
import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import com.yieldbroker.sportcentre.reservation.entity.TennisCourt;
import com.yieldbroker.sportcentre.reservation.repository.ReservationRepository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;

  public List<Reservation> getReservations(Date reservationDate, TennisCourt tennisCourt) {

    return Collections.emptyList();
  }

  public boolean isReservable(Date reservationDate) {

    return  false;
  }

  public void isReservable(Date reservationDate, TennisCourt tennisCourt) {
//    reservationRepository.find
  }

  public Reservation createReservation(Date reservationDate, TennisCourt tennisCourt, Player player) {
    Reservation reservation = new Reservation();
    reservation.setDate(reservationDate);
    reservation.setTennisCourt(tennisCourt);
    reservation.setPlayer(player);
    return reservationRepository.save(reservation);
  }


}

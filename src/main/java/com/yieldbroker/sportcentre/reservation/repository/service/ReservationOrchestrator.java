package com.yieldbroker.sportcentre.reservation.repository.service;

import com.yieldbroker.sportcentre.reservation.entity.Player;
import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import com.yieldbroker.sportcentre.reservation.entity.TennisCourt;
import com.yieldbroker.sportcentre.reservation.event.EventData;
import com.yieldbroker.sportcentre.reservation.event.EventManager;
import com.yieldbroker.sportcentre.reservation.event.EventType;
import com.yieldbroker.sportcentre.reservation.util.Constants;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationOrchestrator {

  private final PlayerService playerService;

  private final TennisCourtService tennisCourtService;

  private final ReservationService reservationService;

  private final EventManager eventManager;

  public List<Reservation> getReservations() {
    return reservationService.getReservations();
  }

  public Reservation createReservation(Date reservationDate, String playerName) {
    if (!isReservable(reservationDate)) {
      return null;
    }

    Player player = playerService.getPlayer(playerName);
    if (player == null) {
      player = playerService.createPlayer(playerName);
    }

    TennisCourt tennisCourt = getReservableTennisCourt(reservationDate);
    Reservation reservation = reservationService.createReservation(reservationDate, tennisCourt, player);

    eventManager.notify(EventType.Notification, buildEventData(reservationDate, tennisCourt));
    return reservation;
  }

  private EventData buildEventData(Date reservationDate, TennisCourt tennisCourt) {
    EventData eventData = new EventData();
    eventData.getParameters().put(Constants.RESERVATION_DATE, reservationDate);
    eventData.getParameters().put(Constants.TENNIS_COURT, tennisCourt);
    return eventData;
  }

  /**
   * Determine if it is possible to make a reservation on the given date.
   */
  private boolean isReservable(Date reservationDate) {
    List<TennisCourt> tennisCourts = tennisCourtService.getTennisCourts();
    return tennisCourts.stream().anyMatch(isReservableTennisCourt(reservationDate));
  }

  private TennisCourt getReservableTennisCourt(Date reservationDate) {
    List<TennisCourt> tennisCourts = tennisCourtService.getTennisCourts();
    return tennisCourts.stream()
        .filter(isReservableTennisCourt(reservationDate))
        .findAny().get();
  }

  /**
   * Determine if the tennis court is reservable on the given date.
   */
  private Predicate<TennisCourt> isReservableTennisCourt(Date reservationDate) {
    return court -> reservationService.isReservable(reservationDate, court);
  }

}

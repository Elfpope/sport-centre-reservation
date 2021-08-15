package com.yieldbroker.sportcentre.reservation.event;

import com.yieldbroker.sportcentre.reservation.entity.Player;
import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import com.yieldbroker.sportcentre.reservation.entity.TennisCourt;
import com.yieldbroker.sportcentre.reservation.repository.service.ReservationService;
import com.yieldbroker.sportcentre.reservation.util.Constants;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {
  private static final DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);

  private final ReservationService reservationService;

  public void update(Date reservationDate, TennisCourt tennisCourt) {
    if (reservationService.isFullyReserved(reservationDate, tennisCourt)) {
      List<Reservation> reservations = reservationService.getReservations(reservationDate, tennisCourt);
      sendFullyReservedNotification(
          tennisCourt,
          reservationDate,
          reservations.stream().map(Reservation::getPlayer).collect(Collectors.toList())
      );
      return;
    }

    sendReservableNotification(reservationDate, tennisCourt);
  }

  private void sendFullyReservedNotification(TennisCourt tennisCourt, Date date, List<Player> players) {
    System.out.println(
        String.format("Tennis Court '%s' is fully reserved by players %s on %s.",
            tennisCourt.getName(),
            players.stream().map(Player::getName).collect(Collectors.joining(",", "[", "]")),
            dateFormat.format(date)
        )
    );
  }

  private void sendReservableNotification(Date reservationDate, TennisCourt tennisCourt) {
    System.out.println(
        String.format("Tennis Court '%s' is still reservable on %s.",
            tennisCourt.getName(),
            dateFormat.format(reservationDate))
    );
  }

}

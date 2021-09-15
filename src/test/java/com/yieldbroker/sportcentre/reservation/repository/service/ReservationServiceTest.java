package com.yieldbroker.sportcentre.reservation.repository.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.yieldbroker.sportcentre.reservation.entity.Player;
import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import com.yieldbroker.sportcentre.reservation.entity.TennisCourt;
import com.yieldbroker.sportcentre.reservation.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

  private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

  @Mock
  private ReservationRepository reservationRepository;

  @InjectMocks
  private ReservationService reservationService;

  @Test
  public void testIsReservable_whenTennisCourtIsReservable() {
    Date date = createDate("01-09-2021");
    TennisCourt tennisCourt = createTennisCourt(1, "Grass Court");

    Reservation reservation1 = createReservation(1, date, createPlayer(1, "Jim"), tennisCourt);
    Reservation reservation2 = createReservation(2, date, createPlayer(2, "Tom"), tennisCourt);
    Reservation reservation3 = createReservation(3, date, createPlayer(3, "Ken"), tennisCourt);
    List<Reservation> reservations = List.of(reservation1, reservation2, reservation3);

    Mockito.when(reservationRepository.findAllByDateAndCourt(date, tennisCourt.getId())).thenReturn(reservations);
    assertTrue(reservationService.isReservable(date, tennisCourt));
    Mockito.verify(reservationRepository).findAllByDateAndCourt(date, tennisCourt.getId());
  }

  @Test
  public void testIsReservable_whenTennisCourtIsNotReservable() {
    Date date = createDate("21-09-2021");
    TennisCourt tennisCourt = createTennisCourt(1, "Grass Court");

    Reservation reservation1 = createReservation(1, date, createPlayer(1, "Jim"), tennisCourt);
    Reservation reservation2 = createReservation(2, date, createPlayer(2, "Tom"), tennisCourt);
    Reservation reservation3 = createReservation(3, date, createPlayer(3, "Ken"), tennisCourt);
    Reservation reservation4 = createReservation(4, date, createPlayer(4, "Pete"), tennisCourt);
    List<Reservation> reservations = List.of(reservation1, reservation2, reservation3, reservation4);

    Mockito.when(reservationRepository.findAllByDateAndCourt(date, tennisCourt.getId())).thenReturn(reservations);
    assertFalse(reservationService.isReservable(date, tennisCourt));
    Mockito.verify(reservationRepository).findAllByDateAndCourt(date, tennisCourt.getId());
  }

  private Date createDate(String date) {
    try {
      return formatter.parse(date);
    } catch (ParseException e) {
      return null;
    }
  }

  private Player createPlayer(long id, String name) {
    Player player = new Player();
    player.setId(id);
    player.setName(name);
    return player;
  }

  private TennisCourt createTennisCourt(long id, String name) {
    TennisCourt court = new TennisCourt();
    court.setId(id);
    court.setName(name);
    return court;
  }

  private Reservation createReservation(long id, Date date, Player player, TennisCourt tennisCourt) {
    Reservation reservation = new Reservation();
    reservation.setId(id);
    reservation.setDate(date);
    reservation.setPlayer(player);
    reservation.setTennisCourt(tennisCourt);
    return reservation;
  }

}
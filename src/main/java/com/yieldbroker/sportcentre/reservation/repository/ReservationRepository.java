package com.yieldbroker.sportcentre.reservation.repository;

import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}

package com.yieldbroker.sportcentre.reservation.repository;

import com.yieldbroker.sportcentre.reservation.entity.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  @Query("SELECT r FROM Reservation r WHERE r.date = :date and r.tennisCourt.id = :tennisCourtId")
  List<Reservation> findAllByDateAndCourt(@Param("date") Date date, @Param("tennisCourtId") long tennisCourtId);
}

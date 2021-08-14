package com.yieldbroker.sportcentre.reservation.repository;

import com.yieldbroker.sportcentre.reservation.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

  Player findByName(String playerName);

}

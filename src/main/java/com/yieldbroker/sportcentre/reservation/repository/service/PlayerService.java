package com.yieldbroker.sportcentre.reservation.repository.service;

import com.yieldbroker.sportcentre.reservation.entity.Player;
import com.yieldbroker.sportcentre.reservation.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {

  private final PlayerRepository playerRepository;

  public Player createPlayer(Player player) {
    return playerRepository.save(player);
  }
}

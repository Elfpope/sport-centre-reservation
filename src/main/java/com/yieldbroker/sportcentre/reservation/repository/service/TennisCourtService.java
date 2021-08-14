package com.yieldbroker.sportcentre.reservation.repository.service;

import com.yieldbroker.sportcentre.reservation.entity.TennisCourt;
import com.yieldbroker.sportcentre.reservation.repository.TennisCourtRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TennisCourtService {

  private final TennisCourtRepository tennisCourtRepository;

  public List<TennisCourt> getTennisCourts() {
    return tennisCourtRepository.findAll();
  }

}

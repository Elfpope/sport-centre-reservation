package com.yieldbroker.sportcentre.reservation.event;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class EventData {

  private Map<String, Object> parameters = new HashMap<>();

}

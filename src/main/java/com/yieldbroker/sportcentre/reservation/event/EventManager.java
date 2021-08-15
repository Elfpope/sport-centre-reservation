package com.yieldbroker.sportcentre.reservation.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class EventManager {

  private final Map<EventType, List<EventListener>> listenerMap = new HashMap<>();

  public void subscribe(EventType eventType, EventListener eventListener) {
    Objects.requireNonNull(eventType, "eventType can't be null");
    Objects.requireNonNull(eventListener, "evenListener can't be null");

    List<EventListener> listeners = listenerMap.getOrDefault(eventType, new ArrayList<>());
    listeners.add(eventListener);
    listenerMap.put(eventType, listeners);
  }

  public void unsubscribe(EventType eventType, EventListener eventListener) {
    Objects.requireNonNull(eventType, "eventType can't be null");
    Objects.requireNonNull(eventListener, "evenListener can't be null");

    List<EventListener> listeners = listenerMap.getOrDefault(eventType, new ArrayList<>());
    listeners.remove(eventListener);
    listenerMap.put(eventType, listeners);
  }

  public void notify(EventType eventType, EventData eventData) {
    List<EventListener> listeners = listenerMap.entrySet().stream()
                                      .filter(entry -> entry.getKey() == eventType)
                                      .map(Entry::getValue)
                                      .findAny()
                                      .orElse(Collections.emptyList());

    listeners.stream().forEach(listener -> listener.update(eventData));
  }
}

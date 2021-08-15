package com.yieldbroker.sportcentre.reservation;

import com.yieldbroker.sportcentre.reservation.event.EventManager;
import com.yieldbroker.sportcentre.reservation.event.EventType;
import com.yieldbroker.sportcentre.reservation.event.NotificationListener;
import com.yieldbroker.sportcentre.reservation.repository.service.ReservationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SportCentreReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportCentreReservationApplication.class, args);
	}

	@Bean
	public NotificationListener notificationListenerBean(ReservationService reservationService) {
		return new NotificationListener(reservationService);
	}

	@Bean
	public EventManager eventManagerBean(NotificationListener notificationListenerBean) {
		EventManager eventManager = new EventManager();
		eventManager.subscribe(EventType.Notification, notificationListenerBean);
		return eventManager;
	}
}

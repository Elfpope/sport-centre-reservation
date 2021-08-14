package com.yieldbroker.sportcentre.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Sport centre is fully booked on the date and no more reservation is allowed.")
public class BookoutException extends RuntimeException {

}

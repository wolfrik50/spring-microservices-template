package io.wulfcodes.hotel.exception;

import lombok.Getter;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public class HotelAbsenceException extends HotelException {
    private static final Integer STATUS_CODE = NOT_FOUND.value();

    private final String hotelId;

    public HotelAbsenceException(String hotelId) {
        super(STATUS_CODE, "Hotel with id '%s' not found!".formatted(hotelId));
        this.hotelId = hotelId;
    }
}

package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Booking;
import com.springboot.Captioner.model.PlayDTOBean;

import java.util.List;

public interface BookingService {
    Booking bookPlay(String userEmail, String playTitle);

    List<PlayDTOBean> findAllBookingsByUserEmail(String userEmail);

    Booking cancelPlay(String userEmail, String playTitle);

    Booking findByUserEmailAndPlayTitle(String userEmail, String playTitle);

}

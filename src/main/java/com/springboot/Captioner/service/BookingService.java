package com.springboot.Captioner.service;

import com.springboot.Captioner.model.*;

import java.util.List;

public interface BookingService {
    Booking bookPlay(String userEmail, String playTitle);
    List<PlayDTOBean> findAllBookingsByUserEmail(String userEmail);

    Booking cancelPlay(String userEmail, String playTitle);
    Booking findByUserEmailAndPlayTitle(String userEmail, String playTitle);

}

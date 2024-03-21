package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Booking;
import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.User;

import java.util.List;

public interface BookingService {
    Booking bookPlay(String userEmail, String playTitle);
    List<Booking> findAllBookingsByUserEmail(String userEmail);
}

package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.User;

public interface BookingService {
    public void saveBooking(String userEmail, String playTitle);
}

package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Booking;
import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.User;
import com.springboot.Captioner.repository.BookingRepository;
import com.springboot.Captioner.repository.PlayRepository;
import com.springboot.Captioner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingServiceImp {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlayRepository playRepository;

    // 预定Play
    public Booking bookPlay(String userEmail, String playTitle) {
        User user = userRepository.findByEmail(userEmail);
        Play play = playRepository.findByTitle(playTitle);

        Booking booking = new Booking(user.getEmail(), play.getTitle());
        return bookingRepository.save(booking);
    }

    // 查找用户的所有预定
    public List<Booking> findAllBookingsByUserEmail(String userEmail) {
        return bookingRepository.findAllByUserEmail(userEmail);
    }
}


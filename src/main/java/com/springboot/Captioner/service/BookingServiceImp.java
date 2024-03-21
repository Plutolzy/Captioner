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
public class BookingServiceImp implements BookingService { // 确保实现了接口

    @Autowired
    private BookingRepository bookingRepository;


    @Override // 确保覆盖接口中的方法
    public Booking bookPlay(String userEmail, String playTitle) {

        Booking booking = new Booking(userEmail, playTitle);
        return bookingRepository.save(booking);
    }

    @Override // 确保覆盖接口中的方法
    public List<Booking> findAllBookingsByUserEmail(String userEmail) {
        return bookingRepository.findAllByUserEmail(userEmail);
    }
}

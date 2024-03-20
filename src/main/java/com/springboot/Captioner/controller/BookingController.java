package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.PlayDTO;
import com.springboot.Captioner.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.Captioner.model.Booking;
import com.springboot.Captioner.service.BookingServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class BookingController {

    @Autowired
    private BookingServiceImp bookingService;
    @Autowired
    private PlayService playService;

    // 预定Play
//    @PostMapping("/booking")
//    public ResponseEntity<?> bookPlay(@RequestParam String email, @RequestParam String title) {
//        try {
//            Booking booking = bookingService.bookPlay(email, title);
//            return ResponseEntity.ok().body(booking);
//        } catch (RuntimeException ex) {
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
    @GetMapping("/booking")
    public List<PlayDTO> getAllPlays() {
        System.out.println("fale");
        return playService.getAllPlays();
    }


    // 获取用户所有预定的Play
    @GetMapping("/booked")
    public List<Booking> getAllBookings(@RequestParam String userEmail) {
        return bookingService.findAllBookingsByUserEmail(userEmail);
    }
}

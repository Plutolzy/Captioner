package com.springboot.Captioner.controller;

import com.google.gson.Gson;
import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.PlayDTO;
import com.springboot.Captioner.model.PlayDTOBean;
import com.springboot.Captioner.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.Captioner.model.Booking;
import com.springboot.Captioner.service.BookingServiceImp;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public List<PlayDTOBean> getAllPlays() {
        System.out.println("fale");

        List<PlayDTOBean> list = new ArrayList<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<PlayDTO> allPlays = playService.getAllPlays();
        System.out.println(allPlays);
        for (int i = 0; i < allPlays.size(); i++) {
            PlayDTOBean playDTOBean = new PlayDTOBean();
            playDTOBean.setId(allPlays.get(i).getId());
            playDTOBean.setTitle(allPlays.get(i).getTitle());
            String startLocalTime = df.format( allPlays.get(i).getStartTime());
            playDTOBean.setStartTime(startLocalTime);
            String entLocalTime = df.format( allPlays.get(i).getEndTime());
            playDTOBean.setEndTime(entLocalTime);
            list.add(playDTOBean);
        }
        return list;
    }


    // 获取用户所有预定的Play
    @GetMapping("/booked")
    public List<Booking> getAllBookings(@RequestParam String userEmail) {
        return bookingService.findAllBookingsByUserEmail(userEmail);
    }
}

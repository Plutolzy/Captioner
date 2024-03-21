package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.*;
import com.springboot.Captioner.service.BookingService;
import com.springboot.Captioner.service.PlayService;
import com.springboot.Captioner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.springboot.Captioner.service.BookingServiceImp;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
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

    @PostMapping("/book")
    public ResponseEntity<?> bookPlay(@RequestBody PlayTitleDTO playTitleDTO) {
        String user_email = userService.getCurrentUserEmail();
        String play_title = playTitleDTO.getTitle();
        bookingService.bookPlay(user_email,play_title);
        UserResponse response = new UserResponse();
        response.setSuccess(true);
        response.setMessage("Booked successfully");
        System.out.println("niubi");
        return ResponseEntity.ok(response);
    }


    // 获取用户所有预定的Play
    @GetMapping("/booked")
    public List<Booking> getAllBookings(@RequestParam String userEmail) {
        return bookingService.findAllBookingsByUserEmail(userEmail);
    }
}

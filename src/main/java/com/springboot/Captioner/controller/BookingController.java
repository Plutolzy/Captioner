package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.PlayDTO;
import com.springboot.Captioner.model.PlayDTOBean;
import com.springboot.Captioner.model.PlayTitleDTO;
import com.springboot.Captioner.model.UserResponse;
import com.springboot.Captioner.service.BookingService;
import com.springboot.Captioner.service.PlayService;
import com.springboot.Captioner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            String startLocalTime = df.format(allPlays.get(i).getStartTime());
            playDTOBean.setStartTime(startLocalTime);
            String endLocalTime = df.format(allPlays.get(i).getEndTime());
            playDTOBean.setEndTime(endLocalTime);
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
    public List<PlayDTOBean> getBookedPlays() {
        String userEmail = userService.getCurrentUserEmail();
        return bookingService.findAllBookingsByUserEmail(userEmail);
    }



    @PostMapping("/cancel")
    public ResponseEntity<?> cancelPlay(@RequestBody PlayTitleDTO playTitleDTO) {
        String user_email = userService.getCurrentUserEmail();
        String play_title = playTitleDTO.getTitle();
        bookingService.cancelPlay(user_email,play_title);
        UserResponse response = new UserResponse();
        response.setSuccess(true);
        response.setMessage("Canceled successfully");
        System.out.println("niubi");
        return ResponseEntity.ok(response);
    }
}

package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Booking;
import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.PlayDTO;
import com.springboot.Captioner.model.PlayDTOBean;
import com.springboot.Captioner.repository.BookingRepository;
import com.springboot.Captioner.repository.PlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImp implements BookingService { // 确保实现了接口

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PlayRepository playRepository;


    @Override // 确保覆盖接口中的方法
    public Booking bookPlay(String userEmail, String playTitle) {

        Booking booking = new Booking(userEmail, playTitle);
        return bookingRepository.save(booking);
    }

    @Override // 确保覆盖接口中的方法
    public List<PlayDTOBean> findAllBookingsByUserEmail(String userEmail) {
        System.out.println("Finding all bookings for user with email: " + userEmail);
        List<String> playTitles = bookingRepository.findPlayTitlesByUserEmail(userEmail);
        System.out.println("Found play titles for user: " + playTitles);
        // 用剧目标题在play表中查找剧目信息
        List<PlayDTOBean> bookings = new ArrayList<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (String playTitle : playTitles) {
            System.out.println("Processing booking for play title: " + playTitle);
            Play play = playRepository.findByTitle(playTitle);
            if (play != null) {
                System.out.println("Found play for title: " + playTitle);
                // 将 PlayDTO 对象映射到 PlayDTOBean 对象
                PlayDTOBean playDTOBean = new PlayDTOBean();
                playDTOBean.setId(play.getId());
                playDTOBean.setTitle(play.getTitle());
                String startLocalTime = df.format(play.getStartTime());
                playDTOBean.setStartTime(startLocalTime);
                String endLocalTime = df.format(play.getEndTime());
                playDTOBean.setEndTime(endLocalTime);
                bookings.add(playDTOBean);
            }else {
                System.out.println("No play found for title: " + playTitle);
            }
        }
        System.out.println("bookings:" + bookings);
        return bookings;
    }

    @Override
    public Booking cancelPlay(String userEmail, String playTitle) {
        // 根据用户邮箱地址和剧目标题查询预订记录
         Booking booking = bookingRepository.findByUserEmailAndPlayTitle(userEmail, playTitle);
        System.out.println("Cancel play method called with userEmail: " + userEmail + " and playTitle: " + playTitle);
        if (booking != null) {
            System.out.println("Found booking for userEmail: " + userEmail + " and playTitle: " + playTitle);
            bookingRepository.delete(booking);
        }else {
            System.out.println("No booking found for userEmail: " + userEmail + " and playTitle: " + playTitle);
        }
        return booking;
    }

    @Override
    public Booking findByUserEmailAndPlayTitle(String userEmail, String playTitle){
        return bookingRepository.findByUserEmailAndPlayTitle(userEmail, playTitle);
    }

}

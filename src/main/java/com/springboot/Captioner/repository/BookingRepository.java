package com.springboot.Captioner.repository;

import com.springboot.Captioner.model.Booking;
import com.springboot.Captioner.model.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // 根据用户ID查找预定
    Booking findByUserEmailAndPlayTitle(String userEmail, String playTitle);
    @Query("SELECT b.playTitle FROM Booking b WHERE b.userEmail = :userEmail")
    List<String> findPlayTitlesByUserEmail(@Param("userEmail") String userEmail);
}

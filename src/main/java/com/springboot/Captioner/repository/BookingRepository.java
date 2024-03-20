package com.springboot.Captioner.repository;

import com.springboot.Captioner.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // 根据用户ID查找预定
    List<Booking> findAllByUserEmail(String userEmail);
}

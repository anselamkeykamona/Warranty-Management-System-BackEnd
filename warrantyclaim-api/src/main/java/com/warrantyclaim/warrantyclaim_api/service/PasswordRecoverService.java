package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.entity.User;
import com.warrantyclaim.warrantyclaim_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordRecoverService {

    private final EmailService emailService;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    // Map lưu OTP tạm thời: email → [otp, expiry]
    private final Map<String, OtpData> otpStore = new ConcurrentHashMap<>();

    public PasswordRecoverService(EmailService emailService, UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void sendOtp(String email) {
        if (!userRepo.existsByEmail(email)) throw new RuntimeException("Email không tồn tại");

        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        emailService.sendOtp(email, otp);
        otpStore.put(email, new OtpData(otp, LocalDateTime.now().plusMinutes(5)));
    }

    public void verifyOtp(String email, String otp) {
        OtpData data = otpStore.get(email);
        if (data == null || !data.getCode().equals(otp) || data.getExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP không hợp lệ hoặc đã hết hạn");
        }
    }

    public void resetPassword(String email, String newPassword) {
        User user = userRepo.findByEmail(email).orElseThrow();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
        otpStore.remove(email); // Xoá OTP sau khi dùng
    }

    private static class OtpData {
        private final String code;
        private final LocalDateTime expiry;
        public OtpData(String code, LocalDateTime expiry) {
            this.code = code;
            this.expiry = expiry;
        }
        public String getCode() { return code; }
        public LocalDateTime getExpiry() { return expiry; }
    }
}

package com.ericxie.travel_risk_api.scheduler;


import com.ericxie.travel_risk_api.model.auth.User;
import com.ericxie.travel_risk_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ericxie.travel_risk_api.service.EmailService;

import java.util.List;

@Component
public class EmailScheduler {
    private final EmailService emailService;
    private final UserRepository userRepository;

    public EmailScheduler(EmailService emailService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @Transactional
    @Scheduled(fixedDelay = 120000)
    public void sendWeeklyDigests() {
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            emailService.sendWeeklyDigest(user);
        }
    }
}

package veniamin.backend.atelie.service;

import jakarta.servlet.http.HttpServletRequest;
import veniamin.backend.atelie.entity.User;

public interface MailService {

    void sendUserVerificationMail(User user, HttpServletRequest request);

    void sendPasswordRestoreMail(User user, HttpServletRequest request);
}

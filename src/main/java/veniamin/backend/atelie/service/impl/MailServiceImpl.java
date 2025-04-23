package veniamin.backend.atelie.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.User;
import veniamin.backend.atelie.service.MailService;
import veniamin.backend.atelie.utils.LogsUtils;
import veniamin.backend.atelie.utils.MailUtils;
import veniamin.backend.atelie.utils.UrlPathUtility;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final LogsUtils logsUtils;

    private static final Logger loggerMail = LoggerFactory.getLogger("mailLogger");

//    @Value("${link.to-verify-user}")
//    private String linkToVerify;
//
//    @Value("${link.to-change-password}")
//    private String linkToRestorePassword;

    @Value("${sender.mail}")
    private String fromMail;

    @Value("${sender.mail.token-replace}")
    private String tokenReplaceString;
    @Value("${sender.mail.email-replace}")
    private String emailReplaceString;

    @Override
    public void sendUserVerificationMail(User user, HttpServletRequest request) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String content = MailUtils.ACCOUNT_VERIFY_TEMPLATE;
        String link = UrlPathUtility.getSiteUrl(request) + "/authorize/verification?email=@EMAIL@&token=@TOKEN@";
        link = link.replace(tokenReplaceString, user.getToken());
        link = link.replace(emailReplaceString, user.getEmail());
        content = content.replace(MailUtils.LINK, link);
        try {
            helper.setText(content, true);
            helper.setTo(user.getEmail());
            helper.setSubject(MailUtils.ACCOUNT_VERIFY_HEADER);
            helper.setFrom(fromMail);
            mailSender.send(mimeMessage);

            logsUtils.log(loggerMail, "Send user verification mail (email " + user.getEmail() + ")");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendPasswordRestoreMail(User user, HttpServletRequest request) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String content = MailUtils.CHANGE_PASSWORD_TEMPLATE;
        String link = UrlPathUtility.getSiteUrl(request) + "/recovery?email=@EMAIL@&token=@TOKEN@";
        link = link.replace(tokenReplaceString, user.getToken());
        link = link.replace(emailReplaceString, user.getEmail());
        content = content.replace(MailUtils.LINK, link);
        try {
            helper.setText(content, true);
            helper.setTo(user.getEmail());
            helper.setSubject(MailUtils.ACCOUNT_CHANGE_PASSWORD_HEADER);
            helper.setFrom(fromMail);
            mailSender.send(mimeMessage);

            logsUtils.log(loggerMail, "Send password restore mail (email " + user.getEmail() + ")");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
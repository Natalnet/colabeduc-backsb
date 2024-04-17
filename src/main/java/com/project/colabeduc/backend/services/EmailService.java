package com.project.colabeduc.backend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendPasswordResetEmail(String to, String newPassword) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        try {
            helper.setTo(to);
            helper.setSubject("Recuperação de Senha");
    
            String htmlContent = "<html>"
                    + "<body style=\"font-family: Arial, sans-serif;\">"
                    + "<h2 style=\"color: #007bff;\">Recuperação de Senha</h2>"
                    + "<p>Olá,</p>"
                    + "<p>Você solicitou a redefinição da sua senha. Abaixo está o seu novo token de recuperação de senha:</p>"
                    + "<p style=\"font-size: 20px; background-color: #f0f0f0; padding: 10px;\">" + newPassword + "</p>"
                    + "<p>Se você não solicitou esta redefinição de senha, por favor, ignore este e-mail.</p>"
                    + "<p>Atenciosamente,<br/>ColabEduc Team</p>"
                    + "</body>"
                    + "</html>";
    
            helper.setText(htmlContent, true);
    
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
}


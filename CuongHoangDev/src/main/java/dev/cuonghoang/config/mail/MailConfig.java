package dev.cuonghoang.config.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Properties;

/**
 * Mail Configuration
 * Cấu hình Email
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app.mail")
public class MailConfig {

    /**
     * Mail from address
     */
    private String from = "noreply@cuonghoang.dev";

    /**
     * Mail templates configuration
     */
    private Templates templates = new Templates();

    @Data
    public static class Templates {
        private String welcome = "mail-templates/welcome.html";
        private String passwordReset = "mail-templates/password-reset.html";
        private String contactConfirmation = "mail-templates/contact-confirmation.html";
    }

    /**
     * JavaMailSender Bean Configuration
     * This bean will be auto-configured by Spring Boot if mail properties are set
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        // These will be overridden by application.yml properties
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("your-email@gmail.com");
        mailSender.setPassword("your-app-password");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        return mailSender;
    }

    /**
     * Mail Template Processing Helper
     */
    public String processTemplate(TemplateEngine templateEngine, String templateName, Context context) {
        return templateEngine.process(templateName, context);
    }
}

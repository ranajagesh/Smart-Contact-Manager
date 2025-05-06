package com.scm.services;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

    // we can provide more implementation
    void sendEmailWithHtml();

    void sendEmailWithAttachment();
}

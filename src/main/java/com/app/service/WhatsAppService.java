package com.app.service;

import com.app.config.WhatsAppConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    private final WhatsAppConfig whatsAppConfig;

    @Autowired
    public WhatsAppService(WhatsAppConfig whatsAppConfig) {
        this.whatsAppConfig = whatsAppConfig;
    }

    public String sendWhatsAppMessage(String to, String messageBody) {
        try {
            Message message = Message.creator(
                            new PhoneNumber("whatsapp:" + to),  // Receiver's WhatsApp number
                            new PhoneNumber("whatsapp:" + whatsAppConfig.getTwilioWhatsAppNumber()), // Twilio WhatsApp number
                            messageBody)
                    .create();

            return "WhatsApp message sent successfully. Message ID: " + message.getSid();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send WhatsApp message: " + e.getMessage();
        }
    }
}


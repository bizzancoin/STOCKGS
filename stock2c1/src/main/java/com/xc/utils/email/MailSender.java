package com.xc.utils.email;

import com.xc.utils.PropertiesUtil;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender {
    private final Properties prop = new Properties();
    private final Session session;
    private final Message msg;
    private final Transport transport;

    public static class Builder {
        private final String mailContent;
        private final String toAddress;
        private String debug = "false";
        private String auth = "true";
        private String host = "smtp.163.com";
        private String protocol = "smtp";

        private String subject = PropertiesUtil.getProperty("admin.auth.email.subject");

        private String fromAddress = PropertiesUtil.getProperty("admin.auth.email");

        private String fromCount = PropertiesUtil.getProperty("admin.auth.email");

        private String fromPassword = PropertiesUtil.getProperty("admin.auth.email.pwd");

        public Builder Debug(String debug) {
            this.debug = debug;
            return this;
        }

        public Builder Subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder Auth(String auth) {
            this.auth = auth;
            return this;
        }

        public Builder Host(String host) {
            this.host = host;
            return this;
        }

        public Builder FromCount(String fromCount) {
            this.fromCount = fromCount;
            return this;
        }

        public Builder FromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
            return this;
        }

        public Builder FromPassword(String fromPassword) {
            this.fromPassword = fromPassword;
            return this;
        }

        public Builder(String mailContent, String toAddress) {
            this.mailContent = mailContent;
            this.toAddress = toAddress;
        }

        public Builder Protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public MailSender send() throws Exception {
            return new MailSender(this);
        }
    }

    private MailSender(Builder builder) throws Exception {
        this.prop.setProperty("mail.debug", builder.debug);
        this.prop.setProperty("mail.smtp.auth", builder.auth);
        this.prop.setProperty("mail.host", builder.host);
        this.prop.setProperty("mail.transport.protocol", builder.protocol);
        this.prop.put("mail.smtp.socketFactory.port", "465");
        this.prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        this.prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        this.prop.setProperty("mail.smtp.socketFactory.port", "465");
        this.session = Session.getInstance(this.prop);
        this.msg = (Message) new MimeMessage(this.session);

        this.transport = this.session.getTransport();
        this.msg.setSubject(builder.subject);
        this.msg.setFrom((Address) new InternetAddress(builder.fromAddress, "通知邮件"));
        this.transport.connect(builder.fromCount, builder.fromPassword);

        this.msg.setContent(builder.mailContent, "text/html;charset=utf-8");

        this.transport.sendMessage(this.msg, new Address[]{ new InternetAddress(builder.toAddress)});
    }

    public static void main(String[] args) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

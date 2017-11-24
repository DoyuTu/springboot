package com.doyutu.springbootmail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class MailTest extends SpringbootMailApplicationTests{

    private static final Logger log = LoggerFactory.getLogger(MailTest.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private static final String to = "85xxxxx72@qq.com";

    @Test
    public void sendSimpleMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("邮件标题");
        message.setText("邮件内容");
        mailSender.send(message);
    }

    @Test
    public void sendAttachmentsMail(){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //开启多组件上传功能
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("附件邮件");
            StringBuilder sb = new StringBuilder("<h2>我是带<p style='color:red;'>颜色</p>的邮件：</h2>")
                    //添加嵌入图片时必须的HTML格式 src中的'cid:xxx' ，xxx必须对应Inline文件的文件名
                    .append("<body><img src='cid:inner.png' /></body>");
            //开启html支持
            mimeMessageHelper.setText(sb.toString(), true);
            FileSystemResource innerFsr = new FileSystemResource(new File("src/main/resources/static/inner.png"));
            //添加嵌入图片
            mimeMessageHelper.addInline("inner.png", innerFsr);
            FileSystemResource fsr = new FileSystemResource(new File("src/main/resources/static/dog.png"));
            //添加附件
            mimeMessageHelper.addAttachment(fsr.getFilename(), fsr);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);

    }

}

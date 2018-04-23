package com.shop.controllers.administratorSite.books.file;

import com.shop.configuration.ApplicationProperties;
import com.shop.data.tables.Picture;
import com.shop.others.RepositoriesAccess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.URL;

@Controller
@RequestMapping("administratorSite/books")
public class FileUploadActions {

    public static void deletePicture(String name) {
        try {
            Picture picture = RepositoriesAccess.picturesRepository.findByName(name);
            if (picture != null)
                RepositoriesAccess.picturesRepository.delete(picture.getId());

            File file = new File(ApplicationProperties.PICTURE_PATH + name);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void downloadImage(String sourceUrl, String targetDirectory, String name)
            throws IOException {
        URL imageUrl = new URL(sourceUrl);
        try (InputStream imageReader = new BufferedInputStream(imageUrl.openStream());
             OutputStream imageWriter = new BufferedOutputStream(
                     new FileOutputStream(targetDirectory + File.separator + name))) {
            int readByte;
            while ((readByte = imageReader.read()) != -1) {
                imageWriter.write(readByte);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping("upload")
    public String upload() {
        return "upload";
    }

    @RequestMapping(value = "uploadFil", method = RequestMethod.POST)
    public String uploadFIle(@RequestParam("name") String name) {
        return "upload";
    }
}

package com.shop.data.services.Picture;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Picture;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.junit.Assert.fail;


public class PicturesOperationsTest {
    private PictureOperations pictureSaver;

    @Before
    public void setUp() {
        pictureSaver = new PictureOperations();
    }

    @Test
    public void uploadFile() throws Exception {
        Picture picture = new Picture(ApplicationConfig.PICTURE_PATH, "name", "jpg");
        byte[] file = new byte[1];
        file[0] = ' ';
        MultipartFile multipartFile = new MockMultipartFile(picture.getName(), file);

        pictureSaver.uploadFileHandlerPicture(picture, multipartFile);

        File f = new File(picture.getPath() + picture.getName() + picture.getFileType());
        if (f.exists() && !f.isDirectory())
            pictureSaver.deletePicture(picture);
        else
            fail("the picture wasn't created");

        if (f.exists() && !f.isDirectory())
            fail("picture still exists");
    }

    @Test
    public void downloadImage() throws Exception {
        Picture picture = new Picture(ApplicationConfig.PICTURE_PATH, "name", "jpg");
        String url = "https://avatars2.githubusercontent.com/u/15995737?v=3&u=5a9b47ecf84c0e09d28f023a8f634586d91a1d7d&s=400";
        pictureSaver.downloadImage(url, picture);

        File f = new File(picture.getPath() + picture.getName() + picture.getFileType());
        if (f.exists() && !f.isDirectory())
            pictureSaver.deletePicture(picture);
        else
            fail("the picture wasn't created");

        if (f.exists() && !f.isDirectory())
            fail("picture still exists");
    }

}
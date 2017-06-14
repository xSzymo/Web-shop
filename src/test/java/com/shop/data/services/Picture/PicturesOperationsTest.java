package com.shop.data.services.Picture;

import com.shop.configuration.ApplicationProperties;
import com.shop.data.tables.Picture;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.fail;

/*
TODO - project doesnt read application.properties. It needs DataBaseTestConf to set proper
 */
public class PicturesOperationsTest {
    private PictureOperations pictureSaver;

    @Before
    public void setUp() {
        pictureSaver = new PictureOperations();
    }

    @Test
    public void uploadFile() throws Exception {
        Picture picture = new Picture(ApplicationProperties.PICTURE_PATH, "name", "jpg");
        pictureSaver.deletePicture(picture);

        byte[] file = new byte[1];
        file[0] = ' ';
        MultipartFile multipartFile = new MockMultipartFile(picture.getName(), file);

        pictureSaver.uploadFile(picture, multipartFile);

        Path existPicture = Paths.get(picture.getPath() + picture.getName() + picture.getFileType());
        if (Files.exists(existPicture))
            pictureSaver.deletePicture(picture);
        else
            fail("the picture wasn't created");

        existPicture = Paths.get(picture.getPath() + picture.getName() + picture.getFileType());
        if (Files.exists(existPicture))
            fail("picture still exists");
    }

    @Test
    public void downloadImage() throws Exception {
        Picture picture = new Picture(ApplicationProperties.PICTURE_PATH, "name", "jpg");
        pictureSaver.deletePicture(picture);

        String url = "https://avatars2.githubusercontent.com/u/15995737?v=3&u=5a9b47ecf84c0e09d28f023a8f634586d91a1d7d&s=400";
        pictureSaver.downloadImage(url, picture);

        Path existPicture = Paths.get(picture.getPath() + picture.getName() + picture.getFileType());
        if (Files.exists(existPicture))
            pictureSaver.deletePicture(picture);
        else
            fail("the picture wasn't created");

        existPicture = Paths.get(picture.getPath() + picture.getName() + picture.getFileType());
        if (Files.exists(existPicture))
            fail("picture still exists");
    }

}

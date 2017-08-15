package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.configuration.ApplicationProperties;
import com.shop.data.tables.Picture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class PicturesServiceTest extends DataBaseTestConfiguration {
    private final String URL = "https://avatars2.githubusercontent.com/u/15995737?v=3&u=5a9b47ecf84c0e09d28f023a8f634586d91a1d7d&s=400";
    @Autowired
    private PicturesService service;

    private LinkedList<Picture> pictures;

    @Before
    public void beforeEachTest() {
        pictures = createPictureCollection();
        service.delete(pictures);
    }

    @After
    public void afterEachTest() {
        service.delete(pictures);
    }

    @Test
    public void saveOneWithUrl() {
        Picture picture = pictures.getFirst();

        service.save(picture, URL);

        assertTrue(picture.equals(service.findOne(picture.getId())));
        File f = new File(picture.getPath() + picture.getName() + picture.getFileType());
        if (f.exists() && !f.isDirectory())
            service.delete(picture);
        else
            fail("the picture wasn't created");
    }

    @Test
    public void saveOneWithMultipartFile() {
        Picture picture = pictures.getFirst();

        byte[] file = new byte[1];
        file[0] = ' ';
        MultipartFile multipartFile = new MockMultipartFile(picture.getName(), file);

        service.save(picture, multipartFile);

        assertTrue(picture.equals(service.findOne(picture.getId())));
        Path f = Paths.get(picture.getPath() + picture.getName() + picture.getFileType());
        if (Files.exists(f))
            service.delete(picture);
        else
            fail("the picture wasn't created");
    }

    @Test
    public void saveOneWithMultipartFileExistNameInDirectory() {
        Picture picture = pictures.getFirst();
        Picture secondPicture = new Picture(picture.getPath(), picture.getName(), picture.getFileType());

        byte[] file = new byte[1];
        file[0] = ' ';
        MultipartFile multipartFile = new MockMultipartFile(picture.getName(), file);

        service.save(picture, multipartFile);
        service.save(secondPicture, multipartFile);

        assertTrue(picture.equals(service.findOne(picture.getId())));
        assertNull("Error\n(Check your directory, and delete picture with name \"name0\" if exist from last test?)", service.findOne(secondPicture));
        File f = new File(picture.getPath() + picture.getName() + picture.getFileType());
        if (f.exists() && !f.isDirectory())
            service.delete(picture);
        else
            fail("the picture wasn't created");
    }

    @Test
    public void saveOneWithUrlExistNameInDirectory() {
        Picture picture = pictures.getFirst();
        Picture secondPicture = new Picture(picture.getPath(), picture.getName(), picture.getFileType());

        service.save(picture, URL);
        service.save(secondPicture, URL);

        assertTrue(picture.equals(service.findOne(picture)));
        assertNull("Error\n(Check your directory, and delete picture with name \"name0\" if exist from last test?)", service.findOne(secondPicture));
        File f = new File(picture.getPath() + picture.getName() + picture.getFileType());
        if (f.exists() && !f.isDirectory())
            service.delete(picture);
        else
            fail("the picture wasn't created");
    }

    @Test
    public void saveNullURL() {
        Picture actualPicture = null;
        MultipartFile multipartFile = new MockMultipartFile("a", new byte[0]);

        try {
            service.save(actualPicture, multipartFile);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void saveNullMultipartFile() {
        try {
            service.save((Picture) null, "");
        } catch (Exception e) {
            assertNull(e);
        }
    }


    @Test
    public void findOne() {
        Picture picture = pictures.getFirst();
        service.save(picture, URL);

        Picture actualPicture = service.findOne(this.pictures.getFirst());

        assertNotNull(actualPicture);
    }

    @Test
    public void findOneById() {
        service.save(pictures.getFirst(), URL);

        Picture actualPicture = service.findOne(this.pictures.getFirst().getId());

        assertNotNull(actualPicture);
    }

    @Test
    public void findAll() {
        Iterable<Picture> pictures = service.findAll();

        pictures.forEach(
                x ->
                        assertNotNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void findNull() {
        try {
            service.findOne((Picture) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void delete() {
        Picture picture = pictures.getFirst();

        service.save(picture, URL);
        service.delete(picture);


        assertNull(service.findOne(picture.getId()));
        Path p = Paths.get(picture.getPath() + picture.getName() + picture.getFileType());
        boolean exists = Files.exists(p);
        if (exists)
            fail("File exists!");
    }

    @Test
    public void deleteById() {
        Picture picture = pictures.getFirst();

        service.save(picture, URL);
        service.delete(picture.getId());

        assertNull(service.findOne(picture.getId()));
        Path p = Paths.get(picture.getPath() + picture.getName() + picture.getFileType());
        boolean exists = Files.exists(p);
        if (exists)
            fail("File exists!");
    }

    @Test
    public void deleteCollection() {
        LinkedList<Picture> pictures = this.pictures;

        for (int i = 0; i < pictures.size(); i++)
            service.save(pictures.get(i), URL);
        service.delete(pictures);

        pictures.forEach(
                x -> {
                    assertNull(service.findOne(x.getId()));
                    Path p = Paths.get(x.getPath() + x.getName() + x.getFileType());
                    boolean exists = Files.exists(p);
                    if (exists)
                        fail("File exists!");
                }
        );
    }

    @Test
    public void deleteNull() {
        try {
            service.delete((Picture) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    public LinkedList<Picture> createPictureCollection() {
        LinkedList<Picture> actualPicture = new LinkedList<>();
        for (int i = 0; i < 3; i++)
            actualPicture.add(new Picture(ApplicationProperties.PICTURE_PATH, "name" + i, ""));
        return actualPicture;
    }
}

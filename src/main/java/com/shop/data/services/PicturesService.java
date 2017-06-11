package com.shop.data.services;

import com.shop.data.repositories.PicturesRepository;
import com.shop.data.services.Picture.PictureOperations;
import com.shop.data.tables.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.Collection;

@Service
@Transactional
public class PicturesService {
    @Autowired
    private PicturesRepository repository;
    @Autowired
    private PictureOperations pictureOperations;


    public void save(Picture picture, MultipartFile multipartFile) {
        if (picture == null)
            return;

        File[] files = new File(picture.getPath()).listFiles();
        for (File file : files)
            if (file.isFile())
                if (file.getName().equals(picture.getName()))
                    return;

        pictureOperations.uploadFile(picture, multipartFile);
        repository.save(picture);
    }

    public void save(Picture picture, String url) {
        if (picture == null)
            return;

        File[] files = new File(picture.getPath()).listFiles();
        for (File file : files)
            if (file.isFile())
                if (file.getName().equals(picture.getName()))
                    return;

        pictureOperations.downloadImage(url, picture);
        repository.save(picture);
    }

    public Picture findOne(long id) {
        try {
            return repository.findOne(id);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Picture findOne(Picture picture) {
        try {
            return findOne(picture.getId());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Iterable<Picture> findAll() {
        return repository.findAll();
    }

    public void delete(long id) {
        try {
            deleteOperation(repository.findById(id));
        } catch (NullPointerException e) {

        } catch (EmptyResultDataAccessException e) {

        }
    }

    public void delete(Picture picture) {
        try {
            delete(picture.getId());
        } catch (NullPointerException e) {

        }
    }

    public void delete(Collection<Picture> pictures) {
        pictures.forEach(
                x -> {
                    if (x != null)
                        delete(x);
                });
    }

    public void deleteOperation(Picture picture) {
        try {
            pictureOperations.deletePicture(picture);
            repository.delete(picture);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
}

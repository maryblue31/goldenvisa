/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alex
 */


@Service
public class FileHandlingImpl implements FileHandlingInterface {

    @Value("${images.upload.dir}")
    public String uploaddir;

    @Override
    public String storeFileToDisk(MultipartFile file, String filename) {

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String imagename = StringUtils.cleanPath(filename + "." + extension);
        Path copyLocation = Paths
                .get(uploaddir + File.separator + imagename);

        try {
            Files.copy(file.getInputStream(),
                    copyLocation,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(FileHandlingImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imagename;
    }

}
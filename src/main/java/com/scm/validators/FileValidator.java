package com.scm.validators;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator <ValidFile, MultipartFile>{

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 3; //3MB

    // we can change type, height and width also

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
       
        if (file==null || file.isEmpty()) {
            // context.disableDefaultConstraintViolation();
            // context.buildConstraintViolationWithTemplate("File can not be empty").addConstraintViolation();
            return true;  
        }

        // file size
        if (file.getSize()> MAX_FILE_SIZE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size should be less than 3MB").addConstraintViolation();
            return false;
        } 

        // to check resolution

        // try {
        //    BufferedImage BufferedImage = ImageIO.read(file.getInputStream());
        //    if (BufferedImage.getHeight() > 1080 || BufferedImage.getWidth() > 1920) {
            
        //    }
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        return true;
    }

}

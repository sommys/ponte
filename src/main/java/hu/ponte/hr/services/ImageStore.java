package hu.ponte.hr.services;

import hu.ponte.hr.domain.Image;
import hu.ponte.hr.domain.ImageMeta;
import hu.ponte.hr.repository.ImageMetaRepository;
import hu.ponte.hr.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service class for handling image storing related operations
 */
@Service
public class ImageStore {
    /**
     * SignService instance for signing the images
     */
    @Autowired
    SignService signService;
    /**
     * ImageRepository instance for storing images
     */
    @Autowired
    ImageRepository imageRepository;
    /**
     * ImageMetaRepository instance for storing image metadata
     */
    @Autowired
    ImageMetaRepository imageMetaRepository;

    /**
     * Method for storing an image in the {@code file} parameter
     * @param file - the file containing the image
     * @return the result of the image storing ("ok" if everything succeeded)
     */
    @Transactional
    public String storeImage(MultipartFile file) {
        try {
            Image img = new Image();
            img.setBytes(file.getBytes());
            String signature = signService.createSignature(img.getBytes());
            ImageMeta meta = ImageMeta.builder()
                    .mimeType(file.getContentType())
                    .size(file.getSize())
                    .name(file.getOriginalFilename())
                    .digitalSign(signature)
                    .build();
            imageMetaRepository.save(meta);
            img.setMetaData(meta);
            imageRepository.save(img);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "ok";
    }

    /**
     * Finds an image by id
     * @param id - the id of the image
     * @return the found image
     */
    public Image getImageById(String id){
        return imageRepository.getOne(id);
    }

    /**
     * Finds all stored images
     * @return the stored images in a list
     */
    public List<Image> findAll(){
        return imageRepository.findAll();
    }
}

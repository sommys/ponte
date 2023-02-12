package hu.ponte.hr.controller;


import hu.ponte.hr.domain.Image;
import hu.ponte.hr.domain.ImageMeta;
import hu.ponte.hr.services.ImageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for Image related operations
 */
@RestController()
@RequestMapping("api/images")
public class ImagesController {
    /**
     * ImageStore service instance
     */
    @Autowired
    private ImageStore imageStore;

    /**
     * Endpoint for listing the metadata for all stored images
     * @return the list of ImageMeta objects for all stored images
     */
    @GetMapping("meta")
    public List<ImageMeta> listImages() {
        return imageStore.findAll().stream().map(Image::getMetaData).collect(Collectors.toList());
    }

    /**
     * Endpoint for getting a preview for an image
     * @param id - the id of the image
     * @param response - the HTTPServletResponse object in which the preview will be sent
     */
    @GetMapping("preview/{id}")
    public void getImage(@PathVariable("id") String id, HttpServletResponse response) {
        Image img = imageStore.getImageById(id);
        response.setContentType(img.getMetaData().getMimeType());
        try {
            OutputStream os = response.getOutputStream();
            os.write(img.getBytes(), 0, (int) img.getMetaData().getSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

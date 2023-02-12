package hu.ponte.hr.controller.upload;

import hu.ponte.hr.services.ImageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST Controller for uploading files
 */
@Component
@RequestMapping("api/file")
public class UploadController
{
    /**
     * ImageStore service instance
     */
    @Autowired
    ImageStore imageStore;

    /**
     * Endpoint for uploading an image
     * @param file - the file containing the image
     * @return the result of the upload ("ok" if everything succeeded)
     */
    @RequestMapping(value = "post", method = RequestMethod.POST)
    @ResponseBody
    public String handleFormUpload(@RequestParam("file") MultipartFile file) {
        try{
            return imageStore.storeImage(file);
        } catch (Exception e){
            return e.getMessage();
        }
    }
}

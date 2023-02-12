package hu.ponte.hr.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * Entity class for storing the images
 */
@Getter
@Entity
public class Image {
    /**
     * ID of the string
     */
    @Id
    private String id;
    /**
     * Byte array containing the data of the image
     */
    @Setter
    @Lob
    private byte[] bytes;
    /**
     * Metadata for the image see {@link ImageMeta}
     */
    @OneToOne
    private ImageMeta metaData;

    public void setMetaData(ImageMeta metaData) {
        this.metaData = metaData;
        this.id = metaData.getId();
    }
}

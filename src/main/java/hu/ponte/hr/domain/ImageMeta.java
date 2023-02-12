package hu.ponte.hr.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Class for storing image metadata
 * @author zoltan
 */
@Getter
@Builder
@Entity
public class ImageMeta
{
	/**
	 * ID of the image, which is a generated UUID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id;
	/**
	 * Name of the image file
	 */
	private String name;
	/**
	 * MIME type of the image
	 */
	private String mimeType;
	/**
	 * Size of the image (in bytes)
	 */
	private long size;
	/**
	 * Signature of the image
	 */
	@Lob
	private String digitalSign;

	public ImageMeta() {}

	public ImageMeta(String id, String name, String mimeType, long size, String digitalSign) {
		this.id = id;
		this.name = name;
		this.mimeType = mimeType;
		this.size = size;
		this.digitalSign = digitalSign;
	}
}

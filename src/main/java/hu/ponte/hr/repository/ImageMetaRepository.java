package hu.ponte.hr.repository;

import hu.ponte.hr.domain.ImageMeta;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * JPA Repository for the ImageMetas
 */
public interface ImageMetaRepository extends JpaRepository<ImageMeta, String> {
}

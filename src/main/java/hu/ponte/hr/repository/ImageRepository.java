package hu.ponte.hr.repository;

import hu.ponte.hr.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository for the Images
 */
public interface ImageRepository extends JpaRepository<Image, String> {
}

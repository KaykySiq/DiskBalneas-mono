package disk.api.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import disk.api.domain.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{

}

package com.xsis.javapos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(long id);
    Optional<Product> findByName(String name);
    Optional<List<Product>> findByStock(int stock);
    Optional<List<Product>> findByPrice(double price);

    @Query(value = "UPDATE Tbl_M_Product SET stock=:stock "
                    + "WHERE id=:productId "
                    + "RETURNING *", nativeQuery = true)
    public Optional<Product> updateStock(@Param("productId") long productId, @Param("stock") int stock);
}

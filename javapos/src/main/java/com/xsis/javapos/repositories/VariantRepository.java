package com.xsis.javapos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.Variant;
import java.util.List;
import java.util.Map;


@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    Optional<Variant> findByName(String name);
    Optional<Variant> findById(long id);
    Optional<Variant> findByCategoryId(long categoryId);   
    Optional<List<Variant>> findByNameContainsIgnoreCase(String name);
    Optional<List<Variant>> findByDescriptionContainsIgnoreCase(String description);
    Optional<List<Variant>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description);
    Optional<List<Variant>> findByIsDeleted(boolean isDeleted);
    
    @Query(value = "select \r\n" + //
                "\tv.id,\r\n" + //
                "\tv.category_id categoryId,\r\n" + //
                "\tv.\"name\", \r\n" + //
                "\tv.description,\r\n" + //
                "\tc.\"name\" as categoryName,\r\n" + //
                "\tv.is_deleted isDeleted,\r\n" + //
                "\tv.create_by createBy,\r\n" + //
                "\tv.create_date createDate,\r\n" + //
                "\tv.update_by updateBy,\r\n" + //
                "\tv.update_date updateDate \r\n" + //
                "from tbl_m_variant as v\r\n" + //
                "inner join tbl_m_category as c on v.category_id = c.id \r\n" + //
                "where v.is_deleted = false", nativeQuery = true)
    Optional<List<Map<String, Object>>> findAllNative();

    @Query(value = "select \r\n" + //
                "\tv.id,\r\n" + //
                "\tv.category_id categoryId,\r\n" + //
                "\tv.\"name\", \r\n" + //
                "\tv.description,\r\n" + //
                "\tv.is_deleted isDeleted,\r\n" + //
                "\tv.create_by createBy,\r\n" + //
                "\tv.create_date createDate,\r\n" + //
                "\tv.update_by updateBy,\r\n" + //
                "\tv.update_date updateDate \r\n" + //
                "from tbl_m_variant as v\r\n" + //
                "inner join tbl_m_category as c on v.category_id = c.id \r\n" + //
                "where v.is_deleted = false and c.id = :categoryId", nativeQuery = true)
    Optional<List<Map<String, Object>>> findByCategoryIdNative(@Param("categoryId") long categoryId);

    @Query(value = "select \r\n" + //
                "\tv.id,\r\n" + //
                "\tv.category_id categoryId,\r\n" + //
                "\tv.\"name\", \r\n" + //
                "\tv.description,\r\n" + //
                "\tv.is_deleted isDeleted,\r\n" + //
                "\tv.create_by createBy,\r\n" + //
                "\tv.create_date createDate,\r\n" + //
                "\tv.update_by updateBy,\r\n" + //
                "\tv.update_date updateDate \r\n" + //
                "from tbl_m_variant as v\r\n" + //
                "inner join tbl_m_category as c on v.category_id = c.id \r\n" + //
                "where v.is_deleted = false and lower(c.name)=lower(:categoryName)", nativeQuery = true)
    Optional<List<Map<String, Object>>> findByCategoryNameNative(@Param("categoryName") String categoryName);
}

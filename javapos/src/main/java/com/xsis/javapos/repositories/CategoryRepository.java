package com.xsis.javapos.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(long id);
    Optional<Category> findByName(String name);
    Optional<List<Category>> findByNameContainsIgnoreCase(String name);
    Optional<List<Category>> findByDescriptionContainsIgnoreCase(String description);
    Optional<List<Category>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description);
    Optional<List<Category>> findByIsDeleted(boolean isDeleted);    
    @Query(value = "select \r\n" + //
                "\tc.id,\r\n" + //
                "\tc.\"name\",\r\n" + //
                "\tc.description,\r\n" + //
                "\tc.is_deleted isDeleted, \r\n" + //
                "\tc.create_by createBy,\r\n" + //
                "\tc.create_date createDate,\r\n" + //
                "\tc.update_by updateBy,\r\n" + //
                "\tc.update_date updateDate  \r\n" + //
                "from tbl_m_category c\r\n" + //
                "where c.is_deleted = false and c.id = :id", nativeQuery = true)
    Optional<List<Map<String, Object>>> findByIdNative(@Param("id") long id);
}

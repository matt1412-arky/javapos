package com.xsis.javapos.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tbl_M_Category")
public class Category implements List<Category>, List<Category> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 200, unique = true, nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "create_by")
    private int createBy;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_by")
    private int updateBy;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public int getCreateBy() {
        return createBy;
    }
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public int getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
	
    // public Category(long id, String name, String description, boolean isDeleted, int createBy, LocalDateTime createDate,
	// 		int updateBy, LocalDateTime updateDate) {
	// 	Id = id;
	// 	Name = name;
	// 	Description = description;
	// 	IsDeleted = isDeleted;
	// 	CreateBy = createBy;
	// 	CreateDate = createDate;
	// 	UpdateBy = updateBy;
	// 	UpdateDate = updateDate;
	// }
    
    // public Category(Long id, String name, String description) {
    //     this(id, name, description, false, 1, LocalDateTime.now(), 0, null);	
    // }

    // public Category() {
    //     this(1,null,null, false, 0, null, 0, null);
    // }
}

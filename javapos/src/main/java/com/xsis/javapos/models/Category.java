package com.xsis.javapos.models;

import java.time.LocalDateTime;

public class Category {
    private Long Id;
    private String Name;
    private String Description;
    private boolean IsDeleted;
    private int CreateBy;
    private LocalDateTime CreateDate;
    private int UpdateBy;
    private LocalDateTime UpdateDate;
	
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        IsDeleted = isDeleted;
    }

    public int getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(int createBy) {
        CreateBy = createBy;
    }

    public LocalDateTime getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        CreateDate = createDate;
    }

    public int getUpdateBy() {
        return UpdateBy;
    }

    public void setUpdateBy(int updateBy) {
        UpdateBy = updateBy;
    }

    public LocalDateTime getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        UpdateDate = updateDate;
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

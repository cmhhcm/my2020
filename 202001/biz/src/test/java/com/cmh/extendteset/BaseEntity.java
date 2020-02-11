package com.cmh.extendteset;

/**
 * Author:meice Huang
 * Time: 2020/2/5 下午3:49
 */
public class BaseEntity extends Entity {

    private String createdBy;

    private String updatedBy;

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}

package com.demo.model;


import io.swagger.annotations.ApiModelProperty;

public class DepartmentVo {
    @ApiModelProperty("部门Id")
    private String departmentId;
    @ApiModelProperty("部门名称")
    private String departmentName;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

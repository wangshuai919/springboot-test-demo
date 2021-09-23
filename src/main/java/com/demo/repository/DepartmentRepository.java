package com.demo.repository;

import com.demo.model.DepartmentVo;

import java.util.List;

public interface DepartmentRepository {
    List<DepartmentVo> getDepartmentByName(String depName);
}

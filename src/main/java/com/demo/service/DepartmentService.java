package com.demo.service;

import com.demo.model.DepartmentVo;

import java.util.List;

public interface DepartmentService {
   List<DepartmentVo> getDepartmentListByPage();

   List<DepartmentVo> findDepartmentByName(String name);

    DepartmentVo save(String id, String name);
}

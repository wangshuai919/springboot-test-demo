package com.demo.repository;

import com.demo.model.DepartmentVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("departmentRepository")
public class InMemoryDepartmentRepository implements  DepartmentRepository{
    @Override
    public List<DepartmentVo> getDepartmentByName(String depName) {
        DepartmentVo departmentA = new DepartmentVo();
        departmentA.setDepartmentName("A");
        departmentA.setDepartmentId("1001");

        DepartmentVo departmentB = new DepartmentVo();
        departmentB.setDepartmentName("B");
        departmentB.setDepartmentId("1002");

        List<DepartmentVo> list = new ArrayList<>();
        list.add(departmentA);
        list.add(departmentB);
        return list;
    }
}

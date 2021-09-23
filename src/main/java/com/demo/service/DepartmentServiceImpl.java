package com.demo.service;

import com.demo.model.DepartmentVo;
import com.demo.repository.DepartmentRepository;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentVo save(String id, String name) {
        DepartmentVo departmentVo = new DepartmentVo();
        departmentVo.setDepartmentId(id);
        departmentVo.setDepartmentName(name);
        return departmentVo;
    }

    @Override
    public List<DepartmentVo> getDepartmentListByPage() {
        String sql = "select * from department";
        Query query = this.entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<String> list = query.getResultList();
        List<DepartmentVo> result = new ArrayList<>();
        for(String depName : list) {
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setDepartmentName(depName);
            result.add(departmentVo);
        }
        return result;
    }

    @Override
    public List<DepartmentVo> findDepartmentByName(String name) {
        return departmentRepository.getDepartmentByName(name);
    }
}

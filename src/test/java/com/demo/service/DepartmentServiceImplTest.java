package com.demo.service;

import com.demo.model.DepartmentVo;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@SpringBootTest
//@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Test
    void getDepartmentListByPage() {
        Query query = mock(Query.class, RETURNS_SMART_NULLS);
        SQLQuery sqlQuery = mock(SQLQuery.class, RETURNS_SMART_NULLS);
        List<String> result = new ArrayList<>();
        result.add("1");
        result.add("2");
        when(entityManager.createNativeQuery(any())).thenReturn(query);
        when(query.unwrap(SQLQuery.class)).thenReturn(sqlQuery);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        when(query.getResultList()).thenReturn(result);
        List<DepartmentVo> resultList = departmentService.getDepartmentListByPage();
        assertThat(resultList.size()).isEqualTo(2);
    }
}
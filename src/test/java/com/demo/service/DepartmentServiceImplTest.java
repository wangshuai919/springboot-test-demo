package com.demo.service;

import com.demo.model.DepartmentVo;
import org.hibernate.SQLQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EntityManager entityManager;

    //说明：InjectMocks这个注解，可以使其它mock对象，注入到加了@InjectMocks注解的对象中
    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Test
    void getDepartmentListByPage() {
        List<String> result = new ArrayList<>();
        result.add("D01");
        result.add("D02");

        /*
            query执行unwrap方法时，返回了SQLQuery对象，此时mock出的query对象无法执行，
            所以重新mock了一个SQLQuery对象，并且对象不为空
         */
        SQLQuery sqlQuery = mock(SQLQuery.class);
        Query query = mock(Query.class);
        when(entityManager.createNativeQuery(any())).thenReturn(query);
        when(query.unwrap(SQLQuery.class)).thenReturn(sqlQuery);
        when(query.getResultList()).thenReturn(result);
        List<DepartmentVo> resultList = departmentService.getDepartmentListByPage();
        assertThat(resultList.size()).isEqualTo(2);
    }
}
package com.demo.controller;

import com.demo.MockMvcBaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DepartmentControllerTest extends MockMvcBaseTest {

    @Test
    void shouldReturnDepartmentList() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "A");
        params.add("Id", "1001");

        this.mockMvc.perform(get("/api/department/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(params) // 多个参数同时设置
//                        .param("name", "A01")  //逐个设置参数
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rows[1].departmentId").value("1002"))
                .andReturn();
    }
}
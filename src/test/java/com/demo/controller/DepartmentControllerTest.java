package com.demo.controller;

import com.demo.MockMvcBaseTest;
import com.demo.model.DepartmentVo;
import com.demo.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DepartmentControllerTest extends MockMvcBaseTest {
    @Mock
    private DepartmentService departmentService;
    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

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

    @Test
    void shouldReturnView() throws Exception {
        DepartmentVo departmentVo = new DepartmentVo();
        departmentVo.setDepartmentName("财务");
        departmentVo.setDepartmentId("9999");
        when(departmentService.save(any(), any())).thenReturn(departmentVo);

        //构造request参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id","1001");
        params.add("name", "IT");

        //构造session参数
        HashMap<String, Object> sessionAttr = new HashMap<>();
        sessionAttr.put("userInfo", "Zhang");

        this.mockMvc.perform(post("/api/department/save")
                        .params(params).sessionAttrs(sessionAttr))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/departmentList"))
                .andExpect(model().attribute("id","9999"))
                .andReturn();
    }

    @Test
    void shouldReturnResultStatus() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id","1001");
        params.add("name", "IT");
        this.mockMvc.perform(post("/api/department/update").params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andReturn();
    }

    @Test
    void shouldReturnResultStatusWhenFileUpload() throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream("/test-1.xlsx");
        MockMultipartFile file = new MockMultipartFile("file", inputStream);
        Map<String, String> contentTypeParams = new HashMap<>();
        contentTypeParams.put("param1", "265001916915724");
        MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);

        this.mockMvc.perform(multipart("/api/department/upload").file(file)
                        .contentType(mediaType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andReturn();
    }
}
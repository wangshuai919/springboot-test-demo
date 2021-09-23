package com.demo.controller;

import com.demo.model.DepartmentVo;
import com.demo.model.JsonModel;
import com.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/find")
    @ResponseBody
    public JsonModel findByParam(HttpServletRequest request) {
        String name = request.getParameter("name");
        JsonModel jsonModel = new JsonModel();
        List<DepartmentVo> departmentList = departmentService.findDepartmentByName(name);
        jsonModel.setRows(departmentList);
        return jsonModel;
    }
}

package com.demo.controller;

import com.demo.model.DepartmentVo;
import com.demo.model.JsonModel;
import com.demo.model.ResultStatus;
import com.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/save")
    public String save(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        DepartmentVo departmentVo = departmentService.save(id, name);
        model.addAttribute("id", departmentVo.getDepartmentId());
        model.addAttribute("name", departmentVo.getDepartmentName());
        return "/departmentList";
    }

    @PostMapping("/update")
    @ResponseBody
    public ResultStatus update(HttpServletRequest request) {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setCode("SUCCESS");
        resultStatus.setMessage("成功");
        return resultStatus;
    }
}

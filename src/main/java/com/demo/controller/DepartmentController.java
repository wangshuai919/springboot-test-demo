package com.demo.controller;

import com.demo.model.DepartmentVo;
import com.demo.model.JsonModel;
import com.demo.model.ResultStatus;
import com.demo.service.DepartmentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "部门", tags = "部门接口Demo")
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

    @ApiOperation(value = "更新部门信息", notes = "请求参数为json格式")
    @PostMapping("/update")
    @ResponseBody
    public ResultStatus update(HttpServletRequest request, @RequestBody @ApiParam(name="部门信息",value="传入json格式",required=true) DepartmentVo department) {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setCode("SUCCESS");
        resultStatus.setMessage("成功");
        return resultStatus;
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResultStatus upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getSize());
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setCode("SUCCESS");
        resultStatus.setMessage("成功");
        return resultStatus;
    }
}

package com.ruoyi.web.controller.dept;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.DeptPayorder;
import com.ruoyi.system.domain.dto.PayOrderDto;
import com.ruoyi.system.service.IDeptPayorderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 支付共债Controller
 *
 * @author ruoyi
 * @date 2023-02-06
 */
@Controller
@RequestMapping("/dept/payorder")
public class DeptPayorderController extends BaseController {
    private String prefix = "dept/payorder";

    @Autowired
    private IDeptPayorderService deptPayorderService;

    @RequiresPermissions("system:payorder:view")
    @GetMapping()
    public String payorder() {
        return prefix + "/payorder";
    }

    /**
     * 查询支付共债列表
     */
    @RequiresPermissions("system:payorder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DeptPayorder deptPayorder) {
        startPage();
        if(!getSysUser().isAdmin()){
            deptPayorder.setUserId(getSysUser().getUserId());
        }
        List<DeptPayorder> list = deptPayorderService.selectDeptPayorderList(deptPayorder);
        return getDataTable(list);
    }


    /**
     * 新增支付共债
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 根据姓名查询支付共债三方并返回 Serializable
     */
    @RequiresPermissions("system:payorder:add")
    @Log(title = "支付共债", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public Serializable addSave(@NotNull(message = "查询姓名不能为空") String name,ModelMap mmap) throws JsonProcessingException {
        List<PayOrderDto> list = new ArrayList<>();
        if(name.isEmpty()){
            return  getDataTable(list);
        }

        JSONObject jsonObject = deptPayorderService.insertDeptPayorder(getSysUser().getUserId(),name);
        if (1 == jsonObject.getIntValue("code")) {
            return  AjaxResult.error(jsonObject.getString("msg"));
        }else {
            JSONArray orderByName = jsonObject.getJSONObject("data").getJSONArray("orderByName");
            list = orderByName.toJavaList(PayOrderDto.class);
/** {"monthLoanOrder":2,"dayhRepayOrder":1,"monthRepayOrder":1,"dayLoanOrder":2,"orderByName":[{"bankCode":"6217858400021479364","amount":"3000.00","phone":"11111111111","lastUpdateDate":"2023-04-05 18:45:02","name":"閲戝摬宄?,"merchant":"閲戣嫻鏋?,"id":317588,"orderDate":"2023-04-05 18:43:50","createDate":"2023-04-05 18:45:02","status":"0"},{"bankCode":"","amount":"3000.00","phone":"1","lastUpdateDate":"2023-04-05 18:05:03","name":"閲戝摬宄?,"merchant":"閲戣嫻鏋?,"id":317439,"orderDate":"2023-04-05 18:04:36","createDate":"2023-04-05 18:05:03","status":"1"},{"bankCode":"6217858400021479364","amount":"1560.00","phone":"11111111111","lastUpdateDate":"2023-04-01 17:25:03","name":"閲戝摬宄?,"merchant":"閲戣嫻鏋?,"id":306901,"orderDate":"2023-04-01 17:22:11","createDate":"2023-04-01 17:25:03","status":"0"}]} */

            mmap.put("users", list);
            JSONObject jsonData = jsonObject.getJSONObject("data");
            /*jsonData.remove("orderByName");
             // 创建ObjectMapper对象
            ObjectMapper objectMapper = new ObjectMapper();
             // 将JSON字符串转换为ModelMap对象
            mmap = objectMapper.readValue(jsonData.toJSONString(), ModelMap.class);*/
            PayOrderDto payOrderDto = new PayOrderDto();
            payOrderDto.setMonthLoanOrder(jsonObject.getIntValue("monthLoanOrder"));
            payOrderDto.setMonthRepayOrder(jsonObject.getIntValue("monthRepayOrder"));
            payOrderDto.setDayhRepayOrder(jsonObject.getIntValue("dayhRepayOrder"));
            payOrderDto.setDayLoanOrder(jsonObject.getIntValue("dayLoanOrder"));
            List<PayOrderDto> payDtoList = new ArrayList<>();
            payDtoList.add(payOrderDto);
            mmap.put("payDto", payDtoList);
            /*
            list.add(payOrderDto);*/

            //return getDataTable(list);
            return prefix + "/add";
        }

    }

    /**
     * 根据用户id查看支付共债详情
     */
    @RequiresPermissions("system:payorder:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) throws JsonProcessingException {
        DeptPayorder deptPayorder = deptPayorderService.selectDeptPayorderById(id);
        mmap.put("deptPayorder", deptPayorder);
        JSONObject jsonObject = JSON.parseObject(deptPayorder.getReport());
        JSONArray orderByName = jsonObject.getJSONArray("orderByName");
        List<PayOrderDto> payDtos = orderByName.toJavaList(PayOrderDto.class);
        mmap.put("monthLoanOrder", jsonObject.getInteger("monthLoanOrder"));
        mmap.put("dayhRepayOrder", jsonObject.getInteger("dayhRepayOrder"));
        mmap.put("monthRepayOrder", jsonObject.getInteger("monthRepayOrder"));
        mmap.put("dayLoanOrder", jsonObject.getInteger("dayLoanOrder"));
        mmap.put("payDto", payDtos);

        /**
         // 创建ObjectMapper对象
        ObjectMapper objectMapper = new ObjectMapper();
        // 将JSON字符串转换为ModelMap对象
        ModelMap modelMap = objectMapper.readValue(jsonObject.toJSONString(), ModelMap.class);
         */
        return prefix + "/edit";
    }

    //下面两个接口无用
    /**
     * 修改保存支付共债
     */
    @RequiresPermissions("system:payorder:edit")
    @Log(title = "支付共债", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DeptPayorder deptPayorder) {
        return toAjax(deptPayorderService.updateDeptPayorder(deptPayorder));
    }

    /**
     * 删除支付共债
     */
    @RequiresPermissions("system:payorder:remove")
    @Log(title = "支付共债", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(deptPayorderService.deleteDeptPayorderByIds(ids));
    }

    /**
     * 导出支付共债列表
     */
    @RequiresPermissions("system:payorder:export")
    @Log(title = "支付共债", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DeptPayorder deptPayorder) {
        List<DeptPayorder> list = deptPayorderService.selectDeptPayorderList(deptPayorder);
        ExcelUtil<DeptPayorder> util = new ExcelUtil<DeptPayorder>(DeptPayorder.class);
        return util.exportExcel(list, "支付共债数据");
    }
}

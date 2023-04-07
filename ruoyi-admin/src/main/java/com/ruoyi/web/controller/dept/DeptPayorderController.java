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
    public Serializable addSave(String name, ModelMap mmap) throws JsonProcessingException {
        List<PayOrderDto> list = new ArrayList<>();
        if(name.isEmpty()){
            return  AjaxResult.error("查询姓名不能为空");
        }

        JSONObject jsonObject = deptPayorderService.insertDeptPayorder(getSysUser().getUserId(),name);
        if (1 == jsonObject.getIntValue("code")) {
            return  AjaxResult.error(jsonObject.getString("msg"));
        }else {
            return  AjaxResult.warn("正在生成报告，请稍后查看详细报告");
            //JSONArray orderByName = jsonObject.getJSONObject("data").getJSONArray("orderByName");
            //list = orderByName.toJavaList(PayOrderDto.class)
            //return  getDataTable(list);

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
        PayOrderDto payOrderDto = new PayOrderDto();
        payOrderDto.setMonthLoanOrder(jsonObject.getIntValue("monthLoanOrder"));
        payOrderDto.setMonthRepayOrder(jsonObject.getIntValue("monthRepayOrder"));
        payOrderDto.setDayhRepayOrder(jsonObject.getIntValue("dayhRepayOrder"));
        payOrderDto.setDayLoanOrder(jsonObject.getIntValue("dayLoanOrder"));
        List<PayOrderDto> payDtoList = new ArrayList<>();
        payDtoList.add(payOrderDto);
        mmap.put("payDtoList", payDtoList);
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

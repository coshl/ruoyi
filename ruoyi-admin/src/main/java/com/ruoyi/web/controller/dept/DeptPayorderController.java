package com.ruoyi.web.controller.dept;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.system.api.DeptApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.DeptPayorder;
import com.ruoyi.system.service.IDeptPayorderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
        List<DeptPayorder> list = deptPayorderService.selectDeptPayorderList(deptPayorder);
        return getDataTable(list);
    }

    /**
     * 导出支付共债列表
     */
    @RequiresPermissions("system:payorder:export")
    @Log(title = "支付共债", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DeptPayorder deptPayorder)
    {
        List<DeptPayorder> list = deptPayorderService.selectDeptPayorderList(deptPayorder);
        ExcelUtil<DeptPayorder> util = new ExcelUtil<DeptPayorder>(DeptPayorder.class);
        return util.exportExcel(list, "支付共债数据");
    }

    /**
     * 新增支付共债
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存支付共债
     */
    @RequiresPermissions("system:payorder:add")
    @Log(title = "支付共债", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(String name)
    {

        return deptPayorderService.insertDeptPayorder(name);
    }

    /**
     * 修改支付共债
     */
    @RequiresPermissions("system:payorder:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DeptPayorder deptPayorder = deptPayorderService.selectDeptPayorderById(id);
        mmap.put("deptPayorder", deptPayorder);
        return prefix + "/edit";
    }

    /**
     * 修改保存支付共债
     */
    @RequiresPermissions("system:payorder:edit")
    @Log(title = "支付共债", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DeptPayorder deptPayorder)
    {
        return toAjax(deptPayorderService.updateDeptPayorder(deptPayorder));
    }

    /**
     * 删除支付共债
     */
    @RequiresPermissions("system:payorder:remove")
    @Log(title = "支付共债", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(deptPayorderService.deleteDeptPayorderByIds(ids));
    }
}

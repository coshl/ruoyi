package com.ruoyi.web.controller.dept;

import java.util.List;
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
import com.ruoyi.system.domain.DeptRechange;
import com.ruoyi.system.service.IDeptRechangeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公债充值Controller
 *
 * @author ruoyi
 * @date 2023-02-04
 */
@Controller
@RequestMapping("/dept/rechange")
public class DeptRechangeController extends BaseController
{
    private String prefix = "dept/rechange";

    @Autowired
    private IDeptRechangeService deptRechangeService;

    @RequiresPermissions("system:rechange:view")
    @GetMapping()
    public String rechange()
    {
        return prefix + "/rechange";
    }

    /**
     * 查询公债充值列表
     */
    @RequiresPermissions("system:rechange:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DeptRechange deptRechange)
    {
        startPage();
        if(!getSysUser().isAdmin()){
            deptRechange.setUserId(getSysUser().getUserId());
        }
        List<DeptRechange> list = deptRechangeService.selectDeptRechangeList(deptRechange);
        return getDataTable(list);
    }

    /**
     * 导出公债充值列表
     */
    @RequiresPermissions("system:rechange:export")
    @Log(title = "公债充值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DeptRechange deptRechange)
    {
        List<DeptRechange> list = deptRechangeService.selectDeptRechangeList(deptRechange);
        ExcelUtil<DeptRechange> util = new ExcelUtil<DeptRechange>(DeptRechange.class);
        return util.exportExcel(list, "公债充值数据");
    }

    /**
     * 新增公债充值
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公债充值
     */
    @RequiresPermissions("system:rechange:add")
    @Log(title = "公债充值", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DeptRechange deptRechange)
    {
        deptRechange.setRechangeName(getSysUser().getLoginName());
        return toAjax(deptRechangeService.insertDeptRechange(deptRechange));
    }

    /**
     * 修改公债充值
     */
    @RequiresPermissions("system:rechange:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DeptRechange deptRechange = deptRechangeService.selectDeptRechangeById(id);
        mmap.put("deptRechange", deptRechange);
        return prefix + "/edit";
    }

    /**
     * 修改保存公债充值
     */
    @RequiresPermissions("system:rechange:edit")
    @Log(title = "公债充值", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DeptRechange deptRechange)
    {
        return toAjax(deptRechangeService.updateDeptRechange(deptRechange));
    }

    /**
     * 删除公债充值
     */
    @RequiresPermissions("system:rechange:remove")
    @Log(title = "公债充值", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(deptRechangeService.deleteDeptRechangeByIds(ids));
    }
}

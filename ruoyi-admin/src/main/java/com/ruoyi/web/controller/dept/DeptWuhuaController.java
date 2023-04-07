package com.ruoyi.web.controller.dept;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.DeptWuhua;
import com.ruoyi.system.domain.dto.wuhua.LoanList;
import com.ruoyi.system.domain.dto.wuhua.RadarData;
import com.ruoyi.system.domain.dto.wuhua.RepayList;
import com.ruoyi.system.service.IDeptWuhuaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TreeMap;

/**
 * 五花共债Controller
 *
 * @author ruoyi
 * @date 2023-04-06
 */
@Controller
@RequestMapping("/dept/wuhua")
public class DeptWuhuaController extends BaseController
{
    private String prefix = "dept/wuhua";

    @Autowired
    private IDeptWuhuaService deptWuhuaService;

    @RequiresPermissions("system:wuhua:view")
    @GetMapping()
    public String wuhua()
    {
        return prefix + "/wuhua";
    }

    /**
     * 查询五花共债列表
     */
    @RequiresPermissions("system:wuhua:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DeptWuhua deptWuhua)
    {
        startPage();
        //只能查询当前用户的成功记录
        if(!getSysUser().isAdmin()){
            deptWuhua.setUserId(getSysUser().getUserId());
            deptWuhua.setStatus(1L);
        }
        List<DeptWuhua> list = deptWuhuaService.selectDeptWuhuaList(deptWuhua);
        return getDataTable(list);
    }

    /**
     * 根据姓名查询五花共债三方并返回
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 根据姓名查询五花共债三方并返回
     */
    @RequiresPermissions("system:wuhua:add")
    @Log(title = "五花共债", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(String name, String mobile, String idCard)
    {
        if(name.isEmpty()||mobile.isEmpty()||idCard.isEmpty()){
            return  AjaxResult.error("查询参数不能为空");
        }
        TreeMap user = new TreeMap();
        user.put("name",name);
        user.put("mobile",mobile);
        user.put("idCard",idCard);
        JSONObject jsonObject = deptWuhuaService.insertDeptWuhua(getSysUser().getUserId(),user);
        if (1 == jsonObject.getInt("code")) {
            return  AjaxResult.error(jsonObject.getStr("msg"));
        }else {
            /**rspData.setCode(0);
             rspData.setRows(list);
             rspData.setTotal(new PageInfo(list).getTotal()); */
            //return success(jsonObject.getJSONObject("result"));
            return  AjaxResult.warn("正在生成报告，请稍后查看详细报告");
        }

    }

    /**
     * 根据用户id查看五花共债
     */
    @RequiresPermissions("system:wuhua:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DeptWuhua deptWuhua = deptWuhuaService.selectDeptWuhuaById(id);
        JSONObject jsonObject = JSONUtil.parseObj(deptWuhua.getReport());
        List<LoanList> loanList = jsonObject.getJSONArray("loanList").toList(LoanList.class);
        List<RepayList> repList = jsonObject.getJSONArray("repList").toList(RepayList.class);
        RadarData radarData = jsonObject.getJSONObject("radarData").toBean(RadarData.class);
        mmap.put("loanList", loanList);
        mmap.put("repList", repList);
        mmap.put("radarData", radarData);
        return prefix + "/edit";
    }


    //下面两个接口无用
    /**
     * 修改保存五花共债
     */
    @RequiresPermissions("system:wuhua:edit")
    @Log(title = "五花共债", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DeptWuhua deptWuhua)
    {
        return toAjax(deptWuhuaService.updateDeptWuhua(deptWuhua));
    }

    /**
     * 删除五花共债
     */
    @RequiresPermissions("system:wuhua:remove")
    @Log(title = "五花共债", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(deptWuhuaService.deleteDeptWuhuaByIds(ids));
    }

    /**
     * 导出五花共债列表
     */
    @RequiresPermissions("system:wuhua:export")
    @Log(title = "五花共债", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DeptWuhua deptWuhua)
    {
        List<DeptWuhua> list = deptWuhuaService.selectDeptWuhuaList(deptWuhua);
        ExcelUtil<DeptWuhua> util = new ExcelUtil<DeptWuhua>(DeptWuhua.class);
        return util.exportExcel(list, "五花共债数据");
    }

}

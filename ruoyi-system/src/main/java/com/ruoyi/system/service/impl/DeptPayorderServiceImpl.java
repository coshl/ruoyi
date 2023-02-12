package com.ruoyi.system.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.api.DeptApi;
import com.ruoyi.system.service.IDeptRechangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DeptPayorderMapper;
import com.ruoyi.system.domain.DeptPayorder;
import com.ruoyi.system.service.IDeptPayorderService;
import com.ruoyi.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 支付共债Service业务层处理
 *
 * @author ruoyi
 * @date 2023-02-06
 */
@Service
public class DeptPayorderServiceImpl implements IDeptPayorderService
{
    @Resource
    private DeptPayorderMapper deptPayorderMapper;

    @Autowired
    private IDeptRechangeService deptRechangeService;

    /**
     * 查询支付共债
     *
     * @param id 支付共债主键
     * @return 支付共债
     */
    @Override
    public DeptPayorder selectDeptPayorderById(Long id)
    {
        return deptPayorderMapper.selectDeptPayorderById(id);
    }

    /**
     * 查询支付共债列表
     *
     * @param deptPayorder 支付共债
     * @return 支付共债
     */
    @Override
    public List<DeptPayorder> selectDeptPayorderList(DeptPayorder deptPayorder)
    {
        return deptPayorderMapper.selectDeptPayorderList(deptPayorder);
    }

    /**
     * 新增支付共债
     *
     * @return 结果
     */
    @Override
    public JSONObject insertDeptPayorder(Long userId ,String name)
    {
        Long billing = deptRechangeService.selectBalance(userId);
        //String billing = deptRechangeService.selectBalance(userId,"dept.payorder.back");
        if(billing <= 0L){
            return null;
            //return AjaxResult.error("用户余额不足,请联系管理员充值");
        }

        String result = DeptApi.getPayOrder(name);
        DeptPayorder deptPayorder  = new DeptPayorder();
        deptPayorder.setDeptName(name);
        deptPayorder.setCreateTime(DateUtils.getNowDate());
        deptPayorder.setStatus(0L);
        if(result.isEmpty() || JSON.parseObject(result).getIntValue("code") != 0){
            deptPayorder.setFailCause(result);
            deptPayorder.getStatus();
            return null;
        }
        JSONObject jsonResult = JSON.parseObject(result).getJSONObject("data");
        deptPayorder.setReport(jsonResult.toJSONString());
        deptPayorder.setStatus(1L);
        deptPayorderMapper.insertDeptPayorder(deptPayorder);
        //费用添加
        deptRechangeService.insertBilling(userId,"dept.payorder.back");

        return jsonResult;
    }

    /**
     * 修改支付共债
     *
     * @param deptPayorder 支付共债
     * @return 结果
     */
    @Override
    public int updateDeptPayorder(DeptPayorder deptPayorder)
    {
        deptPayorder.setUpdateTime(DateUtils.getNowDate());
        return deptPayorderMapper.updateDeptPayorder(deptPayorder);
    }

    /**
     * 批量删除支付共债
     *
     * @param ids 需要删除的支付共债主键
     * @return 结果
     */
    @Override
    public int deleteDeptPayorderByIds(String ids)
    {
        return deptPayorderMapper.deleteDeptPayorderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除支付共债信息
     *
     * @param id 支付共债主键
     * @return 结果
     */
    @Override
    public int deleteDeptPayorderById(Long id)
    {
        return deptPayorderMapper.deleteDeptPayorderById(id);
    }
}

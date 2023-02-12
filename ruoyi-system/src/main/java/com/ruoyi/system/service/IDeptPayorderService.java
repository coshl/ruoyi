package com.ruoyi.system.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.DeptPayorder;

/**
 * 支付共债Service接口
 *
 * @author ruoyi
 * @date 2023-02-06
 */
public interface IDeptPayorderService
{
    /**
     * 查询支付共债
     *
     * @param id 支付共债主键
     * @return 支付共债
     */
    public DeptPayorder selectDeptPayorderById(Long id);

    /**
     * 查询支付共债列表
     *
     * @param deptPayorder 支付共债
     * @return 支付共债集合
     */
    public List<DeptPayorder> selectDeptPayorderList(DeptPayorder deptPayorder);

    /**
     * 新增支付共债
     * @param name 支付共债
     * @return
     */
    public JSONObject insertDeptPayorder(Long userId,String name);

    /**
     * 修改支付共债
     *
     * @param deptPayorder 支付共债
     * @return 结果
     */
    public int updateDeptPayorder(DeptPayorder deptPayorder);

    /**
     * 批量删除支付共债
     *
     * @param ids 需要删除的支付共债主键集合
     * @return 结果
     */
    public int deleteDeptPayorderByIds(String ids);

    /**
     * 删除支付共债信息
     *
     * @param id 支付共债主键
     * @return 结果
     */
    public int deleteDeptPayorderById(Long id);
}

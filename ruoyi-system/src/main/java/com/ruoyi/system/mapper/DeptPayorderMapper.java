package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DeptPayorder;

/**
 * 支付共债Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-06
 */
public interface DeptPayorderMapper 
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
     * 
     * @param deptPayorder 支付共债
     * @return 结果
     */
    public int insertDeptPayorder(DeptPayorder deptPayorder);

    /**
     * 修改支付共债
     * 
     * @param deptPayorder 支付共债
     * @return 结果
     */
    public int updateDeptPayorder(DeptPayorder deptPayorder);

    /**
     * 删除支付共债
     * 
     * @param id 支付共债主键
     * @return 结果
     */
    public int deleteDeptPayorderById(Long id);

    /**
     * 批量删除支付共债
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeptPayorderByIds(String[] ids);
}

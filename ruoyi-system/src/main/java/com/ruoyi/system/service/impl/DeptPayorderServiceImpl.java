package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DeptPayorderMapper;
import com.ruoyi.system.domain.DeptPayorder;
import com.ruoyi.system.service.IDeptPayorderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 支付共债Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-06
 */
@Service
public class DeptPayorderServiceImpl implements IDeptPayorderService 
{
    @Autowired
    private DeptPayorderMapper deptPayorderMapper;

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
     * @param deptPayorder 支付共债
     * @return 结果
     */
    @Override
    public int insertDeptPayorder(DeptPayorder deptPayorder)
    {
        deptPayorder.setCreateTime(DateUtils.getNowDate());
        return deptPayorderMapper.insertDeptPayorder(deptPayorder);
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

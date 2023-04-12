package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.DeptRechange;
import com.ruoyi.system.mapper.DeptRechangeMapper;
import com.ruoyi.system.service.IDeptRechangeService;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公债充值Service业务层处理
 *
 * @author ruoyi
 * @date 2023-02-04
 */
@Service
public class DeptRechangeServiceImpl implements IDeptRechangeService
{
    @Autowired
    private DeptRechangeMapper deptRechangeMapper;

    @Autowired
    private ISysConfigService configService;

    /**
     * 查询余额
     */
    @Override
    public Long selectBalance(Long userId)
    {
       return deptRechangeMapper.selectDeptBilling(userId);
    }

    /**
     * 添加计费
     */
    @Async
    @Override
    public void insertBilling(Long userId, String configKey)
    {
        String price = configService.selectConfigByKey(configKey);
        deptRechangeMapper.insertSysUserBilling(userId, Long.valueOf(price));
    }

    @Override
    public Long selectSumDeptRechange(DeptRechange deptRechange) {
        return deptRechangeMapper.selectSumDeptRechange(deptRechange);
    }

    /**
     * 查询公债充值
     *
     * @param id 公债充值主键
     * @return 公债充值
     */
    @Override
    public DeptRechange selectDeptRechangeById(Long id)
    {
        return deptRechangeMapper.selectDeptRechangeById(id);
    }

    /**
     * 查询公债充值列表
     *
     * @param deptRechange 公债充值
     * @return 公债充值
     */
    @Override
    public List<DeptRechange> selectDeptRechangeList(DeptRechange deptRechange)
    {
        return deptRechangeMapper.selectDeptRechangeList(deptRechange);
    }

    /**
     * 新增公债充值
     *
     * @param deptRechange 公债充值
     * @return 结果
     */
    @Override
    public int insertDeptRechange(DeptRechange deptRechange)
    {
        return deptRechangeMapper.insertDeptRechange(deptRechange);
    }

    /**
     * 修改公债充值
     *
     * @param deptRechange 公债充值
     * @return 结果
     */
    @Override
    public int updateDeptRechange(DeptRechange deptRechange)
    {
        return deptRechangeMapper.updateDeptRechange(deptRechange);
    }

    /**
     * 批量删除公债充值
     *
     * @param ids 需要删除的公债充值主键
     * @return 结果
     */
    @Override
    public int deleteDeptRechangeByIds(String ids)
    {
        return deptRechangeMapper.deleteDeptRechangeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除公债充值信息
     *
     * @param id 公债充值主键
     * @return 结果
     */
    @Override
    public int deleteDeptRechangeById(Long id)
    {
        return deptRechangeMapper.deleteDeptRechangeById(id);
    }
}

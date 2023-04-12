package com.ruoyi.system.service;

import com.ruoyi.system.domain.DeptRechange;

import java.util.List;

/**
 * 公债充值Service接口
 *
 * @author ruoyi
 * @date 2023-02-04
 */
public interface IDeptRechangeService
{

    /**
     * 查询计费
     */
    public Long selectBalance(Long userId);



    public void insertBilling(Long userId, String configKey);


    public Long selectSumDeptRechange(DeptRechange deptRechange);

    /**
     * 查询公债充值
     *
     * @param id 公债充值主键
     * @return 公债充值
     */
    public DeptRechange selectDeptRechangeById(Long id);

    /**
     * 查询公债充值列表
     *
     * @param deptRechange 公债充值
     * @return 公债充值集合
     */
    public List<DeptRechange> selectDeptRechangeList(DeptRechange deptRechange);

    /**
     * 新增公债充值
     *
     * @param deptRechange 公债充值
     * @return 结果
     */
    public int insertDeptRechange(DeptRechange deptRechange);

    /**
     * 修改公债充值
     *
     * @param deptRechange 公债充值
     * @return 结果
     */
    public int updateDeptRechange(DeptRechange deptRechange);

    /**
     * 批量删除公债充值
     *
     * @param ids 需要删除的公债充值主键集合
     * @return 结果
     */
    public int deleteDeptRechangeByIds(String ids);

    /**
     * 删除公债充值信息
     *
     * @param id 公债充值主键
     * @return 结果
     */
    public int deleteDeptRechangeById(Long id);
}

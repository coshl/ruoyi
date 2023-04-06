package com.ruoyi.system.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.api.WuhuaApi;
import com.ruoyi.system.domain.DeptWuhua;
import com.ruoyi.system.mapper.DeptWuhuaMapper;
import com.ruoyi.system.service.IDeptRechangeService;
import com.ruoyi.system.service.IDeptWuhuaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

/**
 * 五花共债Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-06
 */
@Service
@Slf4j
public class DeptWuhuaServiceImpl implements IDeptWuhuaService
{
    @Autowired
    private DeptWuhuaMapper deptWuhuaMapper;

    @Autowired
    private IDeptRechangeService deptRechangeService;
    /**
     * 查询五花共债
     *
     * @param id 五花共债主键
     * @return 五花共债
     */
    @Override
    public DeptWuhua selectDeptWuhuaById(Long id)
    {
        return deptWuhuaMapper.selectDeptWuhuaById(id);
    }

    /**
     * 查询五花共债列表
     *
     * @param deptWuhua 五花共债
     * @return 五花共债
     */
    @Override
    public List<DeptWuhua> selectDeptWuhuaList(DeptWuhua deptWuhua)
    {
        return deptWuhuaMapper.selectDeptWuhuaList(deptWuhua);
    }

    /**
     * 新增五花共债
     *
     * @return 结果
     */
    @Override
    public JSONObject insertDeptWuhua(Long userId ,TreeMap user)
    {
        Long billing = deptRechangeService.selectBalance(userId);
        //String billing = deptRechangeService.selectBalance(userId,"dept.payorder.back");
        // 创建一个空的JSONObject
        JSONObject jsonObject = new JSONObject();
        if(billing <= 0L){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "用户余额不足,请联系管理员充值");
            return jsonObject;
            //return AjaxResult.error("用户余额不足,请联系管理员充值");
        }

        DeptWuhua deptWuhua  = new DeptWuhua();
        deptWuhua.setDeptParam(user.toString());
        deptWuhua.setUserId(userId);
        deptWuhua.setCreateTime(DateUtils.getNowDate());
        deptWuhua.setStatus(0L);

        String result = WuhuaApi.getRadar(user);
        log.info("result==================="+result);

        JSONObject jsonResult = JSONUtil.parseObj(result);
        if(jsonResult.containsKey("code") && jsonResult.getInt("code") == 200) {
            JSONObject jsonReport = jsonResult.getJSONObject("result");
            deptWuhua.setReport(jsonReport.toString());
            deptWuhua.setStatus(1L);
            deptWuhuaMapper.insertDeptWuhua(deptWuhua);
            //费用添加
            deptRechangeService.insertBilling(userId,"dept.wuhua.back");
            return jsonResult;
        }else {
            deptWuhua.setFailCause(result);
            deptWuhua.setStatus(0L);
            deptWuhuaMapper.insertDeptWuhua(deptWuhua);
            jsonObject.put("code", 1);
            jsonObject.put("msg", "调用失败,请联系管理员处理");
            return jsonObject;
        }
    }

    /**
     * 修改五花共债
     *
     * @param deptWuhua 五花共债
     * @return 结果
     */
    @Override
    public int updateDeptWuhua(DeptWuhua deptWuhua)
    {
        deptWuhua.setUpdateTime(DateUtils.getNowDate());
        return deptWuhuaMapper.updateDeptWuhua(deptWuhua);
    }

    /**
     * 批量删除五花共债
     *
     * @param ids 需要删除的五花共债主键
     * @return 结果
     */
    @Override
    public int deleteDeptWuhuaByIds(String ids)
    {
        return deptWuhuaMapper.deleteDeptWuhuaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除五花共债信息
     *
     * @param id 五花共债主键
     * @return 结果
     */
    @Override
    public int deleteDeptWuhuaById(Long id)
    {
        return deptWuhuaMapper.deleteDeptWuhuaById(id);
    }
}

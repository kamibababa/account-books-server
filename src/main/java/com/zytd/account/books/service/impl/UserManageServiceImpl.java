package com.zytd.account.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zytd.account.books.model.UserManage;
import com.zytd.account.books.dao.UserManageMapper;
import com.zytd.account.books.param.user.UserManagerListGetParam;
import com.zytd.account.books.service.UserManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Service
public class UserManageServiceImpl extends ServiceImpl<UserManageMapper, UserManage> implements UserManageService {

    /**
     * 根据类型拉取列表
     */
    @Override
    public List<UserManage> queryByTypeAndMemberId(Integer type, Long memberId) {
        LambdaQueryWrapper<UserManage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserManage::getType, type).eq(UserManage::getMemberId, memberId).orderByAsc(UserManage::getSerialNum);
        return list(queryWrapper);
    }

    /**
     * 根据类型拉取分页
     */
    @Override
    public Page<UserManage> pages(UserManagerListGetParam param) {
        LambdaQueryWrapper<UserManage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserManage::getType, param.getType()).eq(UserManage::getMemberId, param.getMemberId()).orderByAsc(UserManage::getSerialNum);
        if(StringUtils.isNotBlank(param.getKeyword())){
            queryWrapper.like(UserManage::getName,param.getKeyword()).or().like(UserManage::getPhone,param.getKeyword());
        }
        Page<UserManage> page = new Page<>();
        page.setCurrent(param.getPageNum());
        page.setSize(param.getPageSize());
        return page(page,queryWrapper);
    }
}

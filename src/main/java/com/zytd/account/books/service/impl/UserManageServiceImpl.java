package com.zytd.account.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zytd.account.books.model.UserManage;
import com.zytd.account.books.dao.UserManageMapper;
import com.zytd.account.books.service.UserManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public Page<UserManage> queryByTypeAndMemberId(Integer type, Long memberId) {
        LambdaQueryWrapper<UserManage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserManage::getType, type).eq(UserManage::getMemberId, memberId).orderByAsc(UserManage::getSerialNum);
        Page<UserManage> page = new Page<>();
        return page(page,queryWrapper);
    }
}

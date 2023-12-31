package com.zytd.account.books.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.common.utils.ThreadLocalUtil;
import com.zytd.account.books.enums.UserManagerTypeEnum;
import com.zytd.account.books.model.UserManage;
import com.zytd.account.books.param.user.*;
import com.zytd.account.books.service.ExpenditureOrderService;
import com.zytd.account.books.service.IncomeOrderService;
import com.zytd.account.books.service.UserManageExtendService;
import com.zytd.account.books.service.UserManageService;
import com.zytd.account.books.vo.user.UserManagerVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserManageExtendServiceImpl implements UserManageExtendService {
    private final UserManageService userManageService;
    private final IncomeOrderService incomeOrderService;
    private final ExpenditureOrderService expenditureOrderService;

    /**
     * 新增
     */
    @Override
    public ResultVO<Boolean> add(UserManagerAddParam param) {
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        UserManage userManage = new UserManage();
        BeanUtils.copyProperties(param, userManage);
        userManage.setMemberId(memberId);
        userManage.setCreateTime(new Date());
        userManage.setUpdateTime(new Date());
        return userManageService.save(userManage) ? ResultVO.success() : ResultVO.error("新增失败");
    }

    /**
     * 编辑
     */
    @Override
    public ResultVO<Boolean> edit(UserManagerEditParam param) {
        UserManage userManage = userManageService.getById(param.getUserManagerId());
        if(Objects.isNull(userManage))  return ResultVO.error("该数据不存在");
        userManage.setName(param.getName());
        userManage.setPhone(param.getPhone());
        userManage.setAddress(param.getAddress());
        userManage.setRemark(param.getRemark());
        userManage.setUpdateTime(new Date());
        userManage.setEnabled(param.getEnabled());
        return userManageService.updateById(userManage) ? ResultVO.success() : ResultVO.error("编辑失败");
    }

    /**
     * 删除
     */
    @Override
    public ResultVO<Boolean> remove(UserManagerRemoveParam param) {
        UserManage userManage = userManageService.getById(param.getUserManagerId());
        if(Objects.isNull(userManage))  return ResultVO.success();
        //若没有被使用，可以被删除
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        if((UserManagerTypeEnum.customer.getCode().equals(userManage.getType()) && incomeOrderService.countByUserId(param.getUserManagerId(), memberId) > 0) ||
                UserManagerTypeEnum.supplier.getCode().equals(userManage.getType()) && expenditureOrderService.countByUserId(param.getUserManagerId(), memberId) > 0)
            return ResultVO.error("该用户已被使用，无法删除");
        userManageService.removeById(userManage);
        return ResultVO.success();
    }

    /**
     * 详情
     */
    @Override
    public ResultVO<UserManagerVO> detail(UserManagerRemoveParam param) {
        UserManage userManage = userManageService.getById(param.getUserManagerId());
        if(Objects.isNull(userManage))  return ResultVO.error("该数据不存在");
        UserManagerVO vo = new UserManagerVO();
        BeanUtils.copyProperties(userManage, vo);
        vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(userManage.getCreateTime()));
        vo.setUserManagerId(userManage.getId());
        return ResultVO.success(vo);
    }

    /**
     * 排序
     */
    @Override
    public ResultVO<Boolean> sort(UserManagerSortParam param) {
        List<UserManage> list = new ArrayList<>();
        param.getSortList().forEach(userManagerSort -> {
            UserManage userManage = new UserManage();
            userManage.setId(userManagerSort.getUserManagerId());
            userManage.setSerialNum(userManagerSort.getSerialNum());
            list.add(userManage);
        });
        return userManageService.updateBatchById(list) ? ResultVO.success() : ResultVO.error("排序失败");
    }

    /**
     * 根据类型拉取列表
     */
    @Override
    public ResultVO<List<UserManagerVO>> getListByType(UserManagerListGetParam param) {
        List<UserManagerVO> voList = new ArrayList<>();
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        List<UserManage> list = userManageService.queryByTypeAndMemberId(param.getType(), memberId);
        list.forEach(userManage -> voList.add(new UserManagerVO(userManage.getId(), userManage.getType(), userManage.getName(), userManage.getPhone(),
                userManage.getAddress(), userManage.getRemark(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(userManage.getCreateTime()),userManage.getEnabled())));
        return ResultVO.success(voList);
    }

    /**
     * 根据类型拉取列表
     */
    @Override
    public ResultVO<Page<UserManagerVO>> page(UserManagerListGetParam param) {
        List<UserManagerVO> voList = new ArrayList<>();
        Long memberId = ThreadLocalUtil.MEMBER_ID_HOLDER.get();
        param.setMemberId(memberId);
        Page<UserManage> pages = userManageService.pages(param);
        pages.getRecords().forEach(userManage ->
                voList.add(new UserManagerVO(userManage.getId(), userManage.getType(), userManage.getName(), userManage.getPhone(),
                userManage.getAddress(), userManage.getRemark(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(userManage.getCreateTime()),userManage.getEnabled())));
        Page<UserManagerVO> page = new Page<>();
        BeanUtils.copyProperties(pages,page);
        page.setRecords(voList);
        return ResultVO.success(page);
    }
}

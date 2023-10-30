package com.zytd.account.books.biz.accountuser.controller;


import com.zytd.account.books.biz.accountuser.bo.AccountUserBo;
import com.zytd.account.books.biz.accountuser.bo.AccountUserPageBO;
import com.zytd.account.books.biz.accountuser.enums.UserStatusEnum;
import com.zytd.account.books.biz.accountuser.enums.UserTypeEnum;
import com.zytd.account.books.biz.accountuser.vo.AccountUserPageVO;
import com.zytd.account.books.biz.accountuser.vo.AccountUserVO;
import com.zytd.account.books.common.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/accountuser")
public class AccountUserController {

    @RequestMapping("/list")
    public AccountUserPageVO list(@RequestBody AccountUserPageBO userPageBO){
        // 参数
        userPageBO.setUserType(UserTypeEnum.C_USER.getCode());
        // 结果
        List<AccountUserVO> users = new ArrayList<>();
        AccountUserVO userVO = new AccountUserVO();
        userVO.setUserId(1);
        userVO.setUsername("小花");
        userVO.setArea("北京市/北京市/东城区");
        userVO.setAreaDetail("我不知道这个是什么地方，记录一下111");
        userVO.setCreateDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        userVO.setStatus(UserStatusEnum.ENABLE.getDesc());
        userVO.setRemark("灰太狼在哪里");
        userVO.setMobile("18888888999");

        AccountUserVO userVO1 = new AccountUserVO();
        userVO1.setUserId(2);
        userVO1.setUsername("小乐");
        userVO1.setArea("北京市/北京市/东城区");
        userVO1.setAreaDetail("我不知道这个是什么地方，记录一下222");
        userVO1.setCreateDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        userVO1.setStatus(UserStatusEnum.ENABLE.getDesc());
        userVO1.setRemark("大西瓜在哪里");
        userVO1.setMobile("18866666669");

        AccountUserVO userVO2 = new AccountUserVO();
        userVO2.setUserId(3);
        userVO2.setUsername("小地瓜");
        userVO2.setArea("北京市/北京市/东城区");
        userVO2.setAreaDetail("我不知道这个是什么地方，记录一下333");
        userVO2.setCreateDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        userVO2.setStatus(UserStatusEnum.ENABLE.getDesc());
        userVO2.setRemark("大西瓜在哪里");
        userVO2.setMobile("18866666668");
        users.add(userVO);users.add(userVO1);users.add(userVO2);
        for (int i = 0; i < 20; i++) {
            AccountUserVO userVO3 = new AccountUserVO();
            BeanUtils.copyProperties(userVO2,userVO3);
            userVO3.setUserId(i + 4);
            users.add(userVO3);
        }
        if(StringUtils.isNotBlank(userPageBO.getUsername())){
            users = users.stream().filter(u -> u.getUsername().contains(userPageBO.getUsername())).collect(Collectors.toList());
        }
        return new AccountUserPageVO(1,10,users.size(), users);
    }

    @RequestMapping("/detail")
    public AccountUserVO list(Integer userId){
        AccountUserPageBO userPageBO = new AccountUserPageBO();
        AccountUserPageVO list = list(userPageBO);
        List<AccountUserVO> voList = list.getRecords().stream().filter(u -> u.getUserId().equals(userId)).collect(Collectors.toList());
        if(!voList.isEmpty()){
           return voList.get(0);
        }
        return null;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody AccountUserBo bo){
        log.info(bo.toString());
        return Result.success();
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody AccountUserBo bo){
        Assert.notNull(bo.getUserId(),"不能为空");
        log.info(bo.toString());
        return Result.success();
    }

    @RequestMapping("/delete")
    public Result delete(Integer userId){
        Assert.notNull(userId,"不能为空");
        log.info(userId.toString());
        return Result.success();
    }
}

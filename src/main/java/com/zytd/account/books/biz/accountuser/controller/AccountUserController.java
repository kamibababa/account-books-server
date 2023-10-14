package com.zytd.account.books.biz.accountuser.controller;


import com.zytd.account.books.biz.accountuser.bo.UserPageBO;
import com.zytd.account.books.biz.accountuser.enums.UserStatusEnum;
import com.zytd.account.books.biz.accountuser.enums.UserTypeEnum;
import com.zytd.account.books.biz.accountuser.vo.UserPageVO;
import com.zytd.account.books.biz.accountuser.vo.UserVO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/accountuser")
public class AccountUserController {



    @RequestMapping("/list")
    public UserPageVO list(@RequestBody UserPageBO userPageBO){
        // 参数
        userPageBO.setUserType(UserTypeEnum.C_USER.getCode());

        // 结果
        UserVO userVO = new UserVO();
        userVO.setUserId(1);
        userVO.setUsername("小红帽");
        userVO.setAddress("超级魅力城2单元01号");
        userVO.setCreateDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        userVO.setEndDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        userVO.setStatus(UserStatusEnum.ENABLE.getDesc());
        userVO.setRemark("灰太狼在哪里");
        userVO.setMobile("9999999999");

        UserVO userVO1 = new UserVO();
        userVO1.setUserId(2);
        userVO1.setUsername("小地瓜");
        userVO1.setAddress("超级瓜瓜城1单元02号");
        userVO1.setCreateDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        userVO1.setEndDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        userVO1.setStatus(UserStatusEnum.ENABLE.getDesc());
        userVO1.setRemark("大西瓜在哪里");
        userVO1.setMobile("000000000");
        return new UserPageVO(1,10,1, Arrays.asList(userVO,userVO1));
    }


}

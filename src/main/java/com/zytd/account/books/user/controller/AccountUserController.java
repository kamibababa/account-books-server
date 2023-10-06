package com.zytd.account.books.user.controller;


import com.zytd.account.books.user.bo.UserPageBO;
import com.zytd.account.books.user.enums.UserStatusEnum;
import com.zytd.account.books.user.enums.UserTypeEnum;
import com.zytd.account.books.user.vo.UserPageVO;
import com.zytd.account.books.user.vo.UserVO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
        return new UserPageVO(1,10,1, Collections.singletonList(userVO));
    }

}

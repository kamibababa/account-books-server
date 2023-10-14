package com.zytd.account.books.acountbooks.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.plugins.Page;
import com.zytd.account.books.acountbooks.bo.AccountBooksPageBO;
import com.zytd.account.books.acountbooks.enums.AccountBookStatusEnum;
import com.zytd.account.books.acountbooks.service.AccountBooksService;
import com.zytd.account.books.acountbooks.vo.AccountBooksPageVO;
import com.zytd.account.books.acountbooks.vo.AccountBooksVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountBooksServiceImpl implements AccountBooksService {


    @Override
    public AccountBooksPageVO list(AccountBooksPageBO bo) {
        String exampleJson = "[\n" +
                "    {\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"one\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"two\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"three\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"four\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"five\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"six\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"seven\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"eight\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"nine\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"ten\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"eleven\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"third\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"third2\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"four2\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"four3\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"four5\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"five1\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"five2\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"我不知道写什么\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"我不知道写什么\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    },{\n" +
                "        \"username\": \"小xx-1号\",\n" +
                "        \"mobile\": \"12333333309\",\n" +
                "        \"address\": \"梦想一号小店\",\n" +
                "        \"remark\": \"我不知道写什么\",\n" +
                "        \"endDate\": \"2999-01-01\",\n" +
                "        \"accountAmount\": \"10000.33\",\n" +
                "        \"status\": \"1\"\n" +
                "    }\n" +
                "]";
        List<AccountBooksVO> accountBooksVOS = JSON.parseObject(exampleJson, new TypeReference<List<AccountBooksVO>>() {
        });
        List<AccountBooksVO> tmpList = new ArrayList<>(accountBooksVOS);
        for (int i = 0; i < 6; i++) {
            for (AccountBooksVO accountBooksVO : tmpList) {
                //模拟条件搜索
                AccountBooksVO accountBooksVO1 = new AccountBooksVO();
                BeanUtils.copyProperties(accountBooksVO, accountBooksVO1);
                accountBooksVOS.add(accountBooksVO1);
            }
        }
        if(StringUtils.isNotBlank(bo.getRemark()) || StringUtils.isNotBlank(bo.getMobile()) || StringUtils.isNotBlank(bo.getUsername())){
            accountBooksVOS = accountBooksVOS.stream().filter(accountBooksVO ->
                    (StringUtils.isNotBlank(bo.getMobile()) && accountBooksVO.getMobile().contains(bo.getMobile()))
                        || (StringUtils.isNotBlank(bo.getUsername()) && accountBooksVO.getUsername().contains(bo.getUsername()))
                        || (StringUtils.isNotBlank(bo.getRemark()) && accountBooksVO.getRemark().contains(bo.getRemark()))
                ).collect(Collectors.toList());
        }
        BigDecimal totalAmount = BigDecimal.ZERO;
        int count = 1;
        for (AccountBooksVO accountBooksVO : accountBooksVOS) {
            accountBooksVO.setId(count++);
            accountBooksVO.setUserId(count);
            accountBooksVO.setArea("北京市/北京市/东城区");
            accountBooksVO.setAreaDetail("我不知道这个是什么地方，记录一下");
            accountBooksVO.setStatus(AccountBookStatusEnum.getByCode(Integer.parseInt(accountBooksVO.getStatus())).getDesc());
            accountBooksVO.setCreateDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            totalAmount = totalAmount.add(accountBooksVO.getAccountAmount());
        }
        int i = bo.getPageNum() * bo.getPageSize();
        int i1 = (bo.getPageNum() - 1) * bo.getPageSize();
        if (i > accountBooksVOS.size()) {
            i = accountBooksVOS.size();
        }
        List<AccountBooksVO> booksVOList = accountBooksVOS.subList(i1, i);
        AccountBooksPageVO page = new AccountBooksPageVO(bo.getPageNum(), bo.getPageSize(), accountBooksVOS.size(), booksVOList);
        page.setTotalAmount(totalAmount);
        return page;
    }
}

package com.zytd.account.books.service.impl;

import com.zytd.account.books.bo.book.AccountBooksPageBO;
import com.zytd.account.books.service.AccountBooksService;
import com.zytd.account.books.vo.book.AccountBooksPageVO;
import com.zytd.account.books.vo.book.AccountBooksVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AccountBooksServiceImpl implements AccountBooksService {


    @Override
    public AccountBooksPageVO list(AccountBooksPageBO bo) {
        AtomicInteger g = new AtomicInteger(0);
        List<AccountBooksVO> accountBooksVOS =  copyExampleList(g);
        if(StringUtils.isNotBlank(bo.getRemark()) || StringUtils.isNotBlank(bo.getMobile()) || StringUtils.isNotBlank(bo.getUsername())){
            accountBooksVOS = accountBooksVOS.stream().filter(accountBooksVO ->
                    (StringUtils.isNotBlank(bo.getMobile()) && accountBooksVO.getMobile().contains(bo.getMobile()))
                        || (StringUtils.isNotBlank(bo.getUsername()) && accountBooksVO.getUsername().contains(bo.getUsername()))
                        || (StringUtils.isNotBlank(bo.getRemark()) && accountBooksVO.getRemark().contains(bo.getRemark()))
                ).collect(Collectors.toList());
        }
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (AccountBooksVO accountBooksVO : accountBooksVOS) {
            totalAmount = totalAmount.add(accountBooksVO.getTotalAmount());
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

    /**
     * 拷贝数据
     * @param i
     * @return
     */
    private List<AccountBooksVO> copyExampleList(AtomicInteger i) {
        List<AccountBooksVO> accountBooksVOS = originExampleList(i);
        List<AccountBooksVO> booksVOS = new ArrayList<>();
        for (int j = 0; j < 30; j++) {
            for (AccountBooksVO accountBooksVO : accountBooksVOS) {
                AccountBooksVO accountBooksVO1 = new AccountBooksVO();
                BeanUtils.copyProperties(accountBooksVO,accountBooksVO1);
                accountBooksVO.setId(nextId(i));
                booksVOS.add(accountBooksVO1);
            }
        }
        return booksVOS;
    }

    /**
     * 示例数据
     * @param i
     * @return
     */
    private List<AccountBooksVO> originExampleList(AtomicInteger i) {
        List<AccountBooksVO> accountBooksVOS = new ArrayList<>();
        // 未结清
        AccountBooksVO booksVO = new AccountBooksVO();
        booksVO.setArea("北京市/北京市/东城区");
        booksVO.setAreaDetail("朝阳街道天街大花园北路自来熟小区99幢105号");
        booksVO.setCreateDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
        booksVO.setId(nextId(i));
        booksVO.setUserId(booksVO.getId());
        booksVO.setUsername("小花");
        booksVO.setMobile("18888888999");
        booksVO.setTotalAmount(new BigDecimal(30000));
        booksVO.setPayAmount(new BigDecimal(10000));
        booksVO.setRemark("我不知道这个地址所以我随便造了个地址，虚拟地址请不要相信");
        booksVO.setStatus(booksVO.getTotalAmount().subtract(booksVO.getPayAmount()).compareTo(BigDecimal.ZERO) > 0 ? 1 : 2);
        booksVO.setBookType(1);
        booksVO.setBookTypeDesc("批发");

        AccountBooksVO.BookDetail bookDetail = new AccountBooksVO.BookDetail();
        bookDetail.setId(nextId(i));
        bookDetail.setAmount(new BigDecimal(12000));
        bookDetail.setWeight(new BigDecimal(1000));
        bookDetail.setName("土豆");

        AccountBooksVO.BookDetail bookDetail1 = new AccountBooksVO.BookDetail();
        bookDetail1.setId(nextId(i));
        bookDetail1.setAmount(new BigDecimal(15000));
        bookDetail1.setWeight(new BigDecimal(1100));
        bookDetail1.setName("西红柿");

        AccountBooksVO.BookDetail bookDetail2 = new AccountBooksVO.BookDetail();
        bookDetail2.setId(nextId(i));
        bookDetail2.setAmount(new BigDecimal(13000));
        bookDetail2.setWeight(new BigDecimal(1200));
        bookDetail2.setName("茄子");
        List<AccountBooksVO.BookDetail> bookDetails = Arrays.asList(bookDetail, bookDetail1, bookDetail2);
        booksVO.setDetails(bookDetails);
        // 已结清：有人员信息
        AccountBooksVO vo = new AccountBooksVO();
        BeanUtils.copyProperties(booksVO, vo);
        vo.setId(nextId(i));
        vo.setUserId(vo.getId());
        vo.setPayAmount(new BigDecimal(300000));
        vo.setRemark(vo.getRemark() + "有人员");
        vo.setMobile("18866666669");
        vo.setUsername("小乐");
        vo.setStatus(vo.getTotalAmount().subtract(vo.getPayAmount()).compareTo(BigDecimal.ZERO) > 0 ? 1 : 2);
        vo.setBookType(1);
        vo.setBookTypeDesc("批发");
        // 已结清：无人员信息
        AccountBooksVO vo1 = new AccountBooksVO();
        BeanUtils.copyProperties(booksVO, vo1);
        vo1.setId(nextId(i));
        vo1.setPayAmount(new BigDecimal(300000));
        vo1.setRemark(vo.getRemark() + "无人员");
        vo1.setUserId(null);
        vo1.setMobile(null);
        vo1.setUsername(null);
        vo1.setStatus(vo1.getTotalAmount().subtract(vo1.getPayAmount()).compareTo(BigDecimal.ZERO) > 0 ? 1 : 2);
        vo1.setBookType(1);
        vo1.setBookTypeDesc("批发");

        accountBooksVOS.add(booksVO);
        accountBooksVOS.add(vo);
        accountBooksVOS.add(vo1);

        return accountBooksVOS;
    }


    public static int nextId(AtomicInteger i){
        return i.incrementAndGet();
    }
}

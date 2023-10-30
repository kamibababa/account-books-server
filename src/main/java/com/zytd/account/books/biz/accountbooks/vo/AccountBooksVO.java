package com.zytd.account.books.biz.accountbooks.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountBooksVO implements Serializable {

    private Integer id;

    private Integer userId;

    private String username;

    private String mobile;

    private String area;

    private String areaDetail;

    private String remark;

    /**
     * 类型：1批发/2零售
     */
    private Integer bookType;

    /**
     * 类型名称
     */
    private String bookTypeDesc;

    /**
     * 状态：1 未结清 2 已结清
     */
    private Integer  status;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 已付金额
     */
    private BigDecimal payAmount;

    private String createDate;

    private List<BookDetail> details;

    @Data
    public static class BookDetail {

        private Integer id;
        /**
         * 名称
         */
        private String name;
        /**
         * 金额：元
         */
        private BigDecimal amount;
        /**
         * 重量：kg
         */
        private BigDecimal weight;
    }

}

package com.zytd.account.books.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MemberInfoVO implements Serializable {
    /**
     * 会员id
     */
    private Long memberId;
}

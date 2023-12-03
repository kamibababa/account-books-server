package com.zytd.account.books.vo.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MemberVO implements Serializable {
    @ApiModelProperty("会员id")
    private Long memberId;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("是否禁用 1-是 2-否")
    private Integer isForbidden;
    @ApiModelProperty("token")
    private String token;
}

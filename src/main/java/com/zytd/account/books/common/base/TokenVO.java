package com.zytd.account.books.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class TokenVO implements Serializable {
    /**
     * token
     */
    private String token;
}

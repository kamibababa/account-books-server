package com.zytd.account.books.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.param.user.*;
import com.zytd.account.books.vo.user.UserManagerVO;

import java.util.List;

public interface UserManageExtendService {
    /**
     * 新增
     */
    ResultVO<Boolean> add(UserManagerAddParam param);

    /**
     * 编辑
     */
    ResultVO<Boolean> edit(UserManagerEditParam param);

    /**
     * 删除
     */
    ResultVO<Boolean> remove(UserManagerRemoveParam param);

    /**
     * 详情
     */
    ResultVO<UserManagerVO> detail(UserManagerRemoveParam param);

    /**
     * 排序
     */
    ResultVO<Boolean> sort(UserManagerSortParam param);

    /**
     * 根据类型拉取列表
     */
    ResultVO<List<UserManagerVO>> getListByType(UserManagerListGetParam param);

    ResultVO<Page<UserManagerVO>> page(UserManagerListGetParam param);
}

package com.zytd.account.books.controller;


import com.zytd.account.books.common.base.ResultVO;
import com.zytd.account.books.param.user.*;
import com.zytd.account.books.service.UserManageExtendService;
import com.zytd.account.books.vo.user.UserManagerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author wl
 * @since 2023-10-30
 */
@Api(value = "用户管理", tags = {"用户管理"})
@RestController
@RequestMapping("/userManage")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserManageController {
    private final UserManageExtendService userManageExtendService;

    @ApiOperation("新增")
    @PostMapping("add")
    public ResultVO<Boolean> add(@RequestBody UserManagerAddParam param){
        return userManageExtendService.add(param);
    }

    @ApiOperation("编辑")
    @PostMapping("edit")
    public ResultVO<Boolean> edit(@RequestBody UserManagerEditParam param){
        return userManageExtendService.edit(param);
    }

    @ApiOperation("删除")
    @PostMapping("remove")
    public ResultVO<Boolean> remove(@RequestBody UserManagerRemoveParam param){
        return userManageExtendService.remove(param);
    }

    @ApiOperation("详情")
    @PostMapping("detail")
    public ResultVO<UserManagerVO> detail(@RequestBody UserManagerRemoveParam param){
        return userManageExtendService.detail(param);
    }

    @ApiOperation("排序")
    @PostMapping("sort")
    public ResultVO<Boolean> sort(@RequestBody UserManagerSortParam param){
        return userManageExtendService.sort(param);
    }

    @ApiOperation("根据类型拉取列表")
    @PostMapping("getListByType")
    public ResultVO<List<UserManagerVO>> getListByType(@RequestBody UserManagerListGetParam param){
        return userManageExtendService.getListByType(param);
    }
}


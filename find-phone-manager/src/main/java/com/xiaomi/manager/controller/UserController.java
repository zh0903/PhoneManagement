package com.xiaomi.manager.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaomi.common.entity.UserEntity;
import com.xiaomi.manager.aop.annotation.CheckAuthority;
import com.xiaomi.manager.entity.PhoneEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xiaomi.manager.service.UserService;
import com.xiaomi.common.utils.PageUtils;
import com.xiaomi.common.utils.R;



/**
 * 
 *
 * @author lyj
 * @email 1499755237@qq.com
 * @date 2021-10-27 10:56:17
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("manager/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/list")
//    @RequiresPermissions("manager:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id获取某个用户信息")
    @GetMapping("/info/{id}")
//   @RequiresPermissions("manager:user:info")
    public R info(@PathVariable("id") Long id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    @ApiOperation(value = "我是谁")
    @GetMapping("/who")
//   @RequiresPermissions("manager:user:info")
    public R who(){
        return userService.whoAmI();
    }



    /**
     * 保存
     */
    @ApiOperation(value = "增加用户")
    @PostMapping("/save")
//    @RequiresPermissions("manager:user:save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "根据id修改用户信息")
    @PostMapping("/update")
//    @RequiresPermissions("manager:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "根据Id删除用户")
    @PostMapping("/delete")
//    @RequiresPermissions("manager:user:delete")
    public R delete(@RequestBody Long[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @ApiOperation(value = "通过邮箱获取用户信息")
    @GetMapping("/getUser/{email}")
//   @RequiresPermissions("manager:phone:info")
    public R info(@PathVariable("email") String email) {
        //temporary
        UserEntity user = userService.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getEmail, email),false);
        return R.ok().setData(user);
    }
}

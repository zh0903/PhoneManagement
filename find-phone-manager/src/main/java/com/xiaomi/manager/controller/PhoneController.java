package com.xiaomi.manager.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaomi.manager.aop.annotation.CheckAuthority;
import com.xiaomi.manager.req.ScanBorrowPhoneReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.xiaomi.manager.entity.PhoneEntity;
import com.xiaomi.manager.service.PhoneService;
import com.xiaomi.common.utils.PageUtils;
import com.xiaomi.common.utils.R;

import javax.validation.Valid;


/**
 * @author lyj
 * @email 1499755237@qq.com
 * @date 2021-10-27 10:56:17
 */
@Api(tags = "手机模块")
@RestController
@RequestMapping("/manager/phone")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    /**
     * 列表
     */
//    @CheckAuthority()
//    @RequiresPermissions("manager:phone:list")
    @ApiOperation(value = "查询所有的手机列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = phoneService.queryPage(params);

        return R.ok().setData(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询某个手机")
    @GetMapping("/info/{id}")
//   @RequiresPermissions("manager:phone:info")
    public R info(@PathVariable("id") Long id) {
        PhoneEntity phone = phoneService.getById(id);

        return R.ok().setData(phone);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "增加手机")
    @PostMapping("/save")
//    @RequiresPermissions("manager:phone:save")
    public R save(@RequestBody PhoneEntity phone) {
        phoneService.save(phone);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "根据id修改手机的信息")
    @PostMapping("/update")
//    @RequiresPermissions("manager:phone:update")
    public R update(@RequestBody PhoneEntity phone) {
        System.out.println(phone.toString());
        System.out.println("=====");

        phoneService.updateById(phone);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id批量删除手机的信息")
    @PostMapping("/delete")
//    @RequiresPermissions("manager:phone:delete")
    public R delete(@RequestBody Long[] ids) {
        phoneService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    @ApiOperation(value = "扫码借手机")
    @PostMapping("/scanBorrowPhone")
    public R scanBorrowPhone(@Valid @RequestBody ScanBorrowPhoneReq req) {
        PhoneEntity phoneEntity = phoneService.getById(req.getPhoneId());
        if(!ObjectUtils.isEmpty(phoneEntity.getHolderUserId()) && phoneEntity.getHolderUserId()==req.getUserId()) {
            return R.ok().put("code", 202);
        }
        else {
            phoneService.scanBorrowPhone(req);
            return R.ok("借入成功");
        }
    }

    @ApiOperation(value = "通过镭雕号获取手机信息")
    @GetMapping("/getPhoneId/{laserNumer}")
//   @RequiresPermissions("manager:phone:info")
    public R info(@PathVariable("laserNumer") String laserNumer) {
        //temporary
        PhoneEntity phone = phoneService.getOne(Wrappers.<PhoneEntity>lambdaQuery().eq(PhoneEntity::getLaserNumer,laserNumer),false);
        return R.ok().setData(phone);
    }

    @ApiOperation(value = "模糊搜索")
    @GetMapping("/fuzzySearch/{name}")
    public R fuzzySearch(@PathVariable("name") String name) {
        List<PhoneEntity> phoneList= phoneService.fuzzySearch(name);
        return R.ok().setData(phoneList);
    }

    @ApiOperation(value = "通过email查询名下手机")
    @GetMapping("/getMyPhone/{email}")
//   @RequiresPermissions("manager:phone:info")
    public R getMyPhone(@PathVariable("email") String email) {
        List<PhoneEntity> phoneList= phoneService.getMyPhone(email);
        return R.ok().setData(phoneList);
    }
}

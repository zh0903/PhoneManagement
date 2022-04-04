package com.xm.manager.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xm.manager.entity.LogEntity;
import com.xm.manager.service.LogService;
import com.xm.common.utils.PageUtils;
import com.xm.common.utils.R;


/**

 *
 * @date 2021-10-27 10:56:17
 */
@Api(tags = "日志模块")
@RestController
@RequestMapping("manager/log")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有日志")
//    @RequiresPermissions("manager:log:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = logService.queryPage(params);

        return R.ok().setData(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据Id查询日志")
    @PostMapping("/info/{id}")
//   @RequiresPermissions("manager:log:info")
    public R info(@PathVariable("id") Long id) {
        LogEntity log = logService.getById(id);

        return R.ok().setData(log);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "增加日志")
    @PostMapping("/save")
//    @RequiresPermissions("manager:log:save")
    public R save(@RequestBody LogEntity log) {
        logService.save(log);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "根据Id修改日志")
    @PostMapping("/update")
//    @RequiresPermissions("manager:log:update")
    public R update(@RequestBody LogEntity log) {
        logService.updateById(log);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "根据Id删除日志")
    @PostMapping("/delete")
//    @RequiresPermissions("manager:log:delete")
    public R delete(@RequestBody Long[] ids) {
        logService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

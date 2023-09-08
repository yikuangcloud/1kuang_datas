package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.service.BaiduGetinfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.BaiduGetEntity;
import io.renren.modules.sys.service.BaiduGetService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-03-06 08:32:26
 */
@RestController
@RequestMapping("sys/baiduget")
public class BaiduGetController {
    @Autowired
    private BaiduGetService baiduGetService;

    @Autowired
    private BaiduGetinfoService baiduGetinfoService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:baiduget:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = baiduGetService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:baiduget:info")
    public R info(@PathVariable("id") Integer id){
        BaiduGetEntity baiduGet = baiduGetService.getById(id);
        return R.ok().put("baiduGet", baiduGet);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:baiduget:save")
    public R save(@RequestBody BaiduGetEntity baiduGet){
        baiduGetService.save(baiduGet);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:baiduget:update")
    public R update(@RequestBody BaiduGetEntity baiduGet){
        ValidatorUtils.validateEntity(baiduGet);
        baiduGetService.updateById(baiduGet);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:baiduget:delete")
    public R delete(@RequestBody Integer[] ids){
        baiduGetService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/pqalibaba")
    public R pqalibaba(){
        baiduGetinfoService.saveBaiDuMessages();
        return R.ok();
    }

}

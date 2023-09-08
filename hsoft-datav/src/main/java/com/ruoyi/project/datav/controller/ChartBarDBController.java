package com.ruoyi.project.datav.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: ChartBarDBController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/3/25 15:18
 */

@RestController
@RequestMapping("/chart/DB/bar")
public class ChartBarDBController {

    @PostMapping(value = "getDistinctOneField")
    public AjaxResult getDistinctOneField(@RequestBody JSONObject jsonObject) {
        return AjaxResult.success();
    }
}

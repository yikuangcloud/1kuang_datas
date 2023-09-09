package io.renren.modules.sys.controller;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.BaiduGetEntity;
import io.renren.modules.sys.service.BaiduGetService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.BaiduGetinfoEntity;
import io.renren.modules.sys.service.BaiduGetinfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 *
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-03-03 14:15:11
 */
@RestController
@RequestMapping("sys/baidugetinfo")
public class BaiduGetinfoController {
    @Autowired
    private BaiduGetinfoService baiduGetinfoService;

    @Autowired
    private BaiduGetService baiduGetService;

    @Value("${exportRootPath}")
    private String exportRootPath;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:baidugetinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = baiduGetinfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/getFile")
    @RequiresPermissions("sys:baidugetinfo:list")
    public  void getFile(@RequestParam("name") String  fileName, HttpServletResponse response){
        List<BaiduGetinfoEntity> list = baiduGetinfoService.list(new QueryWrapper<BaiduGetinfoEntity>().eq("get_id",fileName));
        BaiduGetEntity baiduGetinfo = baiduGetService.getById(fileName);
        try {
            int i=0;
            //创建HSSFWorkbook对象(excel的文档对象)
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet=wb.createSheet("成绩表");
            HSSFRow row2=sheet.createRow(i);
            //创建单元格并设置单元格内容
            row2.createCell(0).setCellValue("Id");
            row2.createCell(1).setCellValue("公司名");
            row2.createCell(2).setCellValue("相关信息链接");
            for(BaiduGetinfoEntity b:list){
                ++i;
                //在sheet里创建第三行
                HSSFRow rowtemp=sheet.createRow(i);
                rowtemp.createCell(0).setCellValue(b.getId());
                rowtemp.createCell(1).setCellValue(b.getTitle());
                rowtemp.createCell(2).setCellValue(b.getInfoUrl());
            }
            OutputStream output=response.getOutputStream();
            response.reset();
            String file = baiduGetinfo.getSearchKey()+".xls";
            response.setHeader("Content-disposition", "attachment; filename=" + new String(file.getBytes("utf-8"),"ISO8859-1"));
            response.setContentType("application/msexcel");
            wb.write(output);
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:baidugetinfo:info")
    public R info(@PathVariable("id") Integer id){
        BaiduGetinfoEntity baiduGetinfo = baiduGetinfoService.getById(id);

        return R.ok().put("baiduGetinfo", baiduGetinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:baidugetinfo:save")
    public R save(@RequestBody BaiduGetinfoEntity baiduGetinfo){
        baiduGetinfoService.save(baiduGetinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:baidugetinfo:update")
    public R update(@RequestBody BaiduGetinfoEntity baiduGetinfo){
        ValidatorUtils.validateEntity(baiduGetinfo);
        baiduGetinfoService.updateById(baiduGetinfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:baidugetinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        baiduGetinfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

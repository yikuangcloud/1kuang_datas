package cn.huanzi.qch.baseadmin.sys.logcontent.controller;

import cn.huanzi.qch.baseadmin.sys.logcontent.service.LogContentService;
import cn.huanzi.qch.baseadmin.sys.sysuser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author laker
 * @since 2021-08-11
 */
@RestController
@RequestMapping("/sys/logcontent/")
public class LogContentController {
    @Autowired(required = false)
    private LogContentService logContentService;
    @Autowired
    private SysUserService userService;

    @GetMapping("logcontent")
    public ModelAndView authority(){
        return new ModelAndView("sys/logcontent/logcontent");
    }


}
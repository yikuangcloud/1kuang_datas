package com.ruoyi.project.datav.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.redis.RedisCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.netty.ChatHandler;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.datav.domain.DatavScreen;
import com.ruoyi.project.datav.domain.DatavShareDto;
import com.ruoyi.project.datav.domain.DatavShareLogs;
import com.ruoyi.project.datav.service.DatavAuthorityService;
import com.ruoyi.project.datav.service.DatavShareLogsService;
import com.ruoyi.project.datav.service.IDatavScreenService;
import com.ruoyi.project.datav.util.BASE64DecodedMultipartFile;
import com.ruoyi.project.datav.util.DatavConfig;
import com.ruoyi.project.datav.util.IdGen;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;

/**
 * 数据大屏Controller
 * 
 * @author 刘佳男[风清扬]
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/datav/screen")
public class DatavScreenController extends BaseController
{
    @Autowired
    private IDatavScreenService datavScreenService;
    @Autowired
    private DatavAuthorityService datavAuthorityService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private DatavShareLogsService datavShareLogsService;
    @Autowired
	private ChatHandler chatHandler;
    @Autowired
    private RedisCache redisCache;
    /**
     * 查询数据大屏列表
     */
    @PreAuthorize("@ss.hasPermi('datav:screen:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavScreen datavScreen)
    {
        
        
        String createBy = datavAuthorityService.getAuthorities(SecurityUtils.getLoginUser().getUser());
        
        //获取用户账户
//        String username = SecurityUtils.getUsername();
//        //管理员用户查询全部，其他账户只查询自己创建的模板
//        if(!username.equals("admin")) {
//        	datavScreen.setCreateBy(username);
//        }
        startPage();
        datavScreen.setCreateBy(createBy);
        List<DatavScreen> list = datavScreenService.selectDatavScreenList(datavScreen);
        return getDataTable(list);
    }

    /**
     * 导出数据大屏列表
     */
    @PreAuthorize("@ss.hasPermi('datav:screen:export')")
    @Log(title = "数据大屏", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DatavScreen datavScreen)
    {
        List<DatavScreen> list = datavScreenService.selectDatavScreenList(datavScreen);
        ExcelUtil<DatavScreen> util = new ExcelUtil<DatavScreen>(DatavScreen.class);
        return util.exportExcel(list, "screen");
    }

    /**
     * 获取数据大屏详细信息
     */
    @PreAuthorize("@ss.hasPermi('datav:screen:query')")
    @GetMapping(value = "/{screenId}")
    public AjaxResult getInfo(@PathVariable("screenId") String screenId)
    {
        //判断redis中是否有缓存记录
        DatavScreen datavScreen = redisCache.getCacheObject(Constants.SCREEN_KEY+screenId);
        if(datavScreen == null ){
            //没有记录从数据库中读取记录
            datavScreen = datavScreenService.selectDatavScreenById(screenId);
            //将记录放至redis中
            redisCache.setCacheObject(Constants.SCREEN_KEY+screenId, datavScreen, Long.valueOf("43200"), TimeUnit.MINUTES);
        }
        return AjaxResult.success(datavScreen);
    }

    /**
     * 新增数据大屏
     */
    @PreAuthorize("@ss.hasPermi('datav:screen:add')")
    @Log(title = "数据大屏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavScreen datavScreen) throws IOException {
        //上传大屏截图图片
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(datavScreen.getCapturePath());
        String screenCapturePath = FileUploadUtils.upload(DatavConfig.getScreenCapturePath(), file);
        datavScreen.setCapturePath(screenCapturePath);
        datavScreen.setScreenId(IdGen.uuid());
        datavScreen.setCreateBy(SecurityUtils.getUsername());
        return toAjax(datavScreenService.insertDatavScreen(datavScreen));
    }

    /**
     * 修改数据大屏
     */
    @PreAuthorize("@ss.hasPermi('datav:screen:edit')")
    @Log(title = "数据大屏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatavScreen datavScreen) throws IOException {
        //删除redis中缓存记录
        redisCache.deleteObject(Constants.SCREEN_KEY+datavScreen.getScreenId());
        //上传大屏截图图片
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(datavScreen.getCapturePath());
        String screenCapturePath = FileUploadUtils.upload(DatavConfig.getScreenCapturePath(), file);
        datavScreen.setCapturePath(screenCapturePath);
        datavScreen.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(datavScreenService.updateDatavScreen(datavScreen));
    }

    /**
     * 删除数据大屏
     */
    @PreAuthorize("@ss.hasPermi('datav:screen:remove')")
    @Log(title = "数据大屏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{screenId}")
    public AjaxResult remove(@PathVariable String screenId)
    {
        //删除redis中缓存记录
        redisCache.deleteObject(Constants.SCREEN_KEY+screenId);
        return toAjax(datavScreenService.deleteDatavScreenById(screenId));
    }
    
//    @PreAuthorize("@ss.hasPermi('datav:screen:remove')")
//    @Log(title = "数据大屏", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{screenIds}")
//    public AjaxResult remove(@PathVariable String[] screenIds)
//    {
//        return toAjax(datavScreenService.deleteDatavScreenByIds(screenIds));
//    }

    /**
     * 复制应用
     */
    @PreAuthorize("@ss.hasPermi('datav:screen:copyApply')")
    @Log(title = "复制应用数据大屏", businessType = BusinessType.INSERT)
    @PostMapping("/copyApply/{screenId}")
    public AjaxResult copyApply(@PathVariable String screenId)
    {
        DatavScreen newView = datavScreenService.selectDatavScreenById(screenId);
        newView.setScreenId(IdGen.uuid());
        newView.setIsTemplate("0");
        newView.setCreateBy(SecurityUtils.getUsername());
        return toAjax(datavScreenService.insertDatavScreen(newView));
    }
    /**
     *分享模板
     */
    @PreAuthorize("@ss.hasPermi('datav:screen:share')")
    @Log(title = "分享复制应用数据大屏", businessType = BusinessType.INSERT)
    @PostMapping("/shareScreen")
    public AjaxResult shareScreen(@RequestBody DatavShareDto datavShareDto) 
    {
    	//获取分享人信息
    	SysUser sysUser = userService.selectUserByUserName(SecurityUtils.getUsername());
    	//获取分享模板信息
    	DatavScreen screen = datavScreenService.selectDatavScreenById(datavShareDto.getScreenId());
    	
    	for (String user : datavShareDto.getUserList()){  
    		DatavScreen newView = new DatavScreen();
    		BeanUtils.copyProperties(screen, newView);
    		String newId = IdGen.uuid();
            newView.setScreenId(newId);
            newView.setIsTemplate("0");//非模板
            newView.setIsPublic("0");//非公开
            newView.setCreateBy(user); 
            newView.setRemark("该模板来自<" + sysUser.getNickName() + ">的分享");
            datavScreenService.insertDatavScreen(newView);
            //记录分享日志
            DatavShareLogs shareLogs = new DatavShareLogs();
            shareLogs.setId(newId);
            shareLogs.setSenderId(sysUser.getUserName());//发送人
            shareLogs.setReceiverId(user);//接收人
            shareLogs.setPrimaryScreenId(screen.getScreenId());//原模板id
            shareLogs.setCopyScreenId(newId);//新模板id
            shareLogs.setMessage(datavShareDto.getRemark());//留言
            shareLogs.setStatus("0");//状态正常
            shareLogs.setDelFlag("0");
            shareLogs.setCreateBy(user);
            shareLogs.setCreateTime(new Date());
            datavShareLogsService.insertShareLogs(shareLogs);
		}
    	
    	// 推送(分享)
    	List<String> list = Arrays.asList(datavShareDto.getUserList());
		String result = list.stream().map(String::valueOf).collect(Collectors.joining(","));
		String sender = userService.selectUserByUserName(SecurityUtils.getUsername()).getNickName();

		String recall = "{\"type\":1, \"receiverIds\":\"" + result + "\",\"sender\": \""+ sender +"\",\"message\": \"" + datavShareDto.getRemark() + "\"}"; 
		System.err.println(recall);
		chatHandler.sendMessageAll(recall);
    	
        return toAjax(datavShareDto.getUserList().length);
    }
    
    @PreAuthorize("@ss.hasPermi('datav:screen:share')")
    @GetMapping("/userlist")
    public AjaxResult getUserList()
    {
        List<SysUser> list = userService.selectShareUserList(new SysUser());
    	
        return AjaxResult.success(list);
    }

    @GetMapping("/templateList")
    public TableDataInfo getTemplateList(){
        startPage();
        List<DatavScreen> list = datavScreenService.selectTemplateList();
        return getDataTable(list);
    }
}

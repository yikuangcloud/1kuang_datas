package cn.huanzi.qch.baseadmin.user.service;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.config.security.PasswordConfig;
import cn.huanzi.qch.baseadmin.sys.sysuser.service.SysUserService;
import cn.huanzi.qch.baseadmin.sys.sysuser.vo.SysUserVo;
import cn.huanzi.qch.baseadmin.util.MD5Util;
import cn.huanzi.qch.baseadmin.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordConfig passwordConfig;

    @Override
    public Result<SysUserVo> updatePassword(String oldPassword, String newPassword) {
        SysUserVo sysUserVo = sysUserService.findByLoginName(SecurityUtil.getLoginUser().getUsername()).getData();
        Result<SysUserVo> result = Result.of(null,false,"修改失败，你输入的原密码错误！");
        //确认旧密码
        if(sysUserVo.getPassword().equals(MD5Util.getMd5(oldPassword))){

            //密码安全策略，弱口令限制
            String msg = passwordConfig.pwdCheck(newPassword);
            if(!"1".equals(msg)){
                result = Result.of(null,false,msg);
                return result;
            }

            //新密码
            sysUserVo.setPassword(MD5Util.getMd5(newPassword));

            //最后修改密码时间
            sysUserVo.setLastChangePwdTime(new Date());

            //调用保存
            result = sysUserService.save(sysUserVo);

            //置空隐私数据
            result.getData().setPassword(null);
        }
        return result;
    }

    @Override
    public Result<SysUserVo> updateUser(SysUserVo sysUserVo) {
        SysUserVo sysUserVo1 = sysUserService.findByLoginName(SecurityUtil.getLoginUser().getUsername()).getData();
        //只允许用户修改这几个选项
        sysUserVo1.setUserName(sysUserVo.getUserName());

        //调用保存
        Result<SysUserVo> result = sysUserService.save(sysUserVo1);

        //置空隐私数据
        result.getData().setPassword(null);

        return result;
    }
}

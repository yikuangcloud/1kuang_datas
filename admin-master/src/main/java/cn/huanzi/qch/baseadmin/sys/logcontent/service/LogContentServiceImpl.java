package cn.huanzi.qch.baseadmin.sys.logcontent.service;

import cn.huanzi.qch.baseadmin.common.service.CommonServiceImpl;
import cn.huanzi.qch.baseadmin.sys.logcontent.pojo.LogContent;
import cn.huanzi.qch.baseadmin.sys.logcontent.vo.LogContentVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laker
 * @since 2021-08-11
 */
@Service
@Transactional
public class LogContentServiceImpl extends CommonServiceImpl<LogContentVo, LogContent, String> implements LogContentService {

}

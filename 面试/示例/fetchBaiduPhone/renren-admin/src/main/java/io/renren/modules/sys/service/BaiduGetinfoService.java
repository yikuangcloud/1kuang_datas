package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.BaiduGetinfoEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-03-03 14:15:11
 */
public interface BaiduGetinfoService extends IService<BaiduGetinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);


    void saveBaiDuMessages() ;
}


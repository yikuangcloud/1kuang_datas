package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.BaiduGetEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-03-06 08:32:26
 */
public interface BaiduGetService extends IService<BaiduGetEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


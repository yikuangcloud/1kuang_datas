package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.BaiduGetDao;
import io.renren.modules.sys.entity.BaiduGetEntity;
import io.renren.modules.sys.service.BaiduGetService;


@Service("baiduGetService")
public class BaiduGetServiceImpl extends ServiceImpl<BaiduGetDao, BaiduGetEntity> implements BaiduGetService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object searchKeyO = params.get("searchKey");
        Object province= params.get("province");
        Object city= params.get("city");
        Object type= params.get("type");
        String searchKey = null;
        if (searchKeyO != null) {
            searchKey = searchKeyO.toString();
        }
        IPage<BaiduGetEntity> page = this.page(
                new Query<BaiduGetEntity>().getPage(params),
                new QueryWrapper<BaiduGetEntity>()
                .like(searchKey!=null,"search_key",searchKey)
                .like(province!=null, "province", params.get("province"))
                        .like(city!=null, "city", params.get("city"))
                        .like(type!=null, "type", params.get("type"))
        );

        return new PageUtils(page);
    }

}

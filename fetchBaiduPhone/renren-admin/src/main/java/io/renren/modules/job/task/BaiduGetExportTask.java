package io.renren.modules.job.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PoiNetUtils;
import io.renren.modules.job.service.ScheduleJobService;
import io.renren.modules.sys.dao.BaiduGetDao;
import io.renren.modules.sys.entity.BaiduGetEntity;
import io.renren.modules.sys.entity.BaiduGetinfoEntity;
import io.renren.modules.sys.service.BaiduGetinfoService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.ServletOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("baiduGetExportTask")
public class BaiduGetExportTask {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    BaiduGetinfoService baiduGetinfoService;



    @Autowired
    private ScheduleJobService scheduleJobService;

    public void exportBaiduInfo() throws IOException {
        try {
                baiduGetinfoService.saveBaiDuMessages();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
        }
    }


}

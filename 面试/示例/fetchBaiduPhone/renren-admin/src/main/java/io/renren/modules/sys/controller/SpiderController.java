package io.renren.modules.sys.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.PoiNetUtils;

import io.renren.common.utils.R;
import io.renren.modules.sys.dao.BaiduGetDao;
import io.renren.modules.sys.entity.BaiduGetEntity;
import io.renren.modules.sys.entity.BaiduGetinfoEntity;
import io.renren.modules.sys.service.BaiduGetinfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SpiderController {
    @Autowired
    BaiduGetinfoService baiduGetinfoService;
    @Autowired
    BaiduGetDao baiduGetDao;

    @Value("${exportRootPath}")
    private String exportRootPath;

    @RequestMapping("/exporth")
    public void exporth(HttpServletResponse response, HttpServletRequest request, HttpSession httpSession, @RequestParam(value = "historyId") Integer historyId, Integer endPage, Integer startPage) throws UnsupportedEncodingException {

        BaiduGetEntity baiduGetEntity = baiduGetDao.selectById(historyId);
        String fileName = baiduGetEntity.getFileName();


        response.addHeader("Content-Disposition", "attachment;filename=" +
                    new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");


        try {
            // path是指欲下载的文件的路径。
            File file = new File(exportRootPath+fileName);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(exportRootPath+fileName));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }





    @ResponseBody
    @RequestMapping("/isHaveHistory")
    public R isHaveHistory(HttpServletRequest request) {
        String str = request.getParameter("str");
        String type = request.getParameter("type");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        Integer historyGet =
                baiduGetDao.selectCount(new QueryWrapper<BaiduGetEntity>()
                        .eq("search_key", str)
                        .eq("province",province)
                        .eq("city",city)
                        .eq("type",type)
                );
        if (historyGet != null && historyGet > 0) {
            return R.error("一天内有获取记录");
        }
        BaiduGetEntity b=new BaiduGetEntity();
        b.setSearchKey(str);
        b.setGetStatus(0);
        b.setType(type);
        b.setProvince(province);
        b.setCity(city);
        baiduGetDao.insert(b);
        return R.ok();
    }

    private void getTel(BaiduGetinfoEntity b, Element element) {
        String text = element.text();
        String numStart =element.text();
        String numEnd =element.text();

        int 空 = text.indexOf(" ");
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(text);
//	判断字符串中是否包含数字
        if(matcher.find()){
            //	获取数字起始位置

            numStart= text.substring(matcher.start());
            int i = numStart.indexOf(" ");
            numEnd=i>0?numStart.substring(0,i):numStart;
        }


        b.setTelInfo(b.getTelInfo() == null ? null : b.getInfoUrl() + "   " + element.text());
        String s = numEnd.replaceAll("[\\D&&[^\\(]&&[^\\)]]", "");
        String tel = b.getTel();
        if (StringUtils.isNotBlank(tel)) {
            b.setTel(b.getTel() + "," + s);
        } else {
            b.setTel(s);
        }
    }

    @ResponseBody
    @RequestMapping("exportStations")
    public HashMap<String, Object> isExport(HttpSession httpSession) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        System.err.println("11111111111111222222222222222222221");


        if (httpSession.getAttribute("exportFlag") == null) {
            hashMap.put("exportFlag", 1);
            return hashMap;
        } else {
            if (httpSession.getAttribute("exportFlag").equals("error")) {
                hashMap.put("exportFlag", 2);
                hashMap.put("historyId", httpSession.getAttribute("historyId"));
                return hashMap;
            }
        }
        if ((boolean) httpSession.getAttribute("exportFlag") == false) {
            hashMap.put("exportFlag", 0);
            return hashMap;
        } else {
            Long endTime = System.currentTimeMillis();
            Long starTime = (Long) httpSession.getAttribute("startTime");
            Long useTime = (endTime - starTime) / 1000;
            Integer exportTime = Integer.valueOf(httpSession.getAttribute("exportTime").toString())
                    - Integer.valueOf(useTime.toString());
            if (exportTime <= 1) {
                exportTime = 1;
            }
            hashMap.put("exportFlag", -1);
            hashMap.put("datasetSize", httpSession.getAttribute("datasetSize"));
            hashMap.put("exportTime", exportTime);
            return hashMap;

        }
    }

    private static void readAndPrintProgress(InputStream is, int len)
            throws IOException {
        int sizeRead = 0;
        byte[] buffer = new byte[1024];

        int tmpSize = 0;
        do {
            sizeRead += tmpSize;
            System.out.println((sizeRead * 100 / len));
            tmpSize = is.read(buffer);
        } while (tmpSize > 0);
    }




}


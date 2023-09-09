package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.PoiNetUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.sys.dao.BaiduGetDao;
import io.renren.modules.sys.dao.BaiduGetinfoDao;
import io.renren.modules.sys.entity.BaiduGetEntity;
import io.renren.modules.sys.entity.BaiduGetinfoEntity;
import io.renren.modules.sys.service.BaiduGetinfoService;
import io.renren.modules.sys.util.DriverUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.*;


@Service("baiduGetinfoService")
public class BaiduGetinfoServiceImpl extends ServiceImpl<BaiduGetinfoDao, BaiduGetinfoEntity> implements BaiduGetinfoService {
    @Autowired(required = false)
    BaiduGetDao baiduGetDao;

    @Value("${exportRootPath}")
    private String exportRootPath;

    public R exportLink(String str, Integer startPage, Integer endPage, HttpServletResponse response) throws IOException {
        List<BaiduGetEntity> baiduGetEntities = baiduGetDao.selectList(
                new QueryWrapper<BaiduGetEntity>().eq("search_key", str));
        boolean fag = false;
        if (baiduGetEntities.size() <= 0) {
            fag = true;
        } else {
            for (BaiduGetEntity b : baiduGetEntities) {
                long time1 = b.getSearchTime().getTime();
                long time2 = new Date().getTime();
                if (time2 - time1 < 86400000l) {
                    int count = this.count(
                            new QueryWrapper<BaiduGetinfoEntity>().eq("get_id", b.getId())
                    );
                    if (count > 0) {
                        return R.error("一天内有同样获取记录");

                    } else {
                        if (b.getGetStatus() == 2 || b.getGetStatus() == 3) {
                            fag = true;
                        }
                    }
                } else {

                    fag = true;
                }
            }
        }
        if (fag) {
            BaiduGetEntity baiduGetEntity = new BaiduGetEntity();
            baiduGetEntity.setSearchKey(str);
            baiduGetEntity.setSearchTime(new Date());
            baiduGetDao.insert(baiduGetEntity);
            return R.ok();
        } else {
            return R.error("一天内有同样获取记录");
        }
    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object searchKeyO = params.get("searchKey");
        String searchKey = null;
        if (searchKeyO != null) {
            searchKey = searchKeyO.toString();
        }
        IPage<BaiduGetinfoEntity> page = this.page(
                new Query<BaiduGetinfoEntity>().getPage(params),
                new QueryWrapper<BaiduGetinfoEntity>()
                        .like(StringUtils.isNotBlank(searchKey), "title", searchKey)
                        .eq("get_id", params.get("getId"))

        );

        return new PageUtils(page);
    }

    private List<BaiduGetinfoEntity> getBaiDuMessage( BaiduGetEntity b) throws IOException, InterruptedException {
        String search = URLEncoder.encode(b.getSearchKey(), "GBK");
        String province = URLEncoder.encode(b.getProvince(), "GBK");
        String city = URLEncoder.encode(b.getCity(), "GBK");
        List<Map<String,String>> contacthref = new ArrayList<>();
        List<BaiduGetinfoEntity> list = new ArrayList<>();
        int page = 1;
        String url = "https://s.1688.com/selloffer/offer_search.htm?keywords=" + search + "&n=y&netType=16&spm=a26352.13672862.searchbox.input&filt=y&beginPage=" + page + "#sm-filtbar";
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        ChromeDriverService chromeDrService = ChromeDriverService.createDefaultService();
        WebDriver driver = new ChromeDriver(chromeDrService);
        driver.get(url);
        Document document = Jsoup.parse(driver.getPageSource());
        addContacthrefList(document,contacthref);
        if(document.select("em.fui-paging-num").first()!=null){
            String totalpage = document.select("em.fui-paging-num").first().text();
            int pagesize = totalpage!=null?Integer.parseInt(totalpage):1;
            for (int i = 2; i <=pagesize; i++) {
                page = i;
                Thread.sleep(10*1000);
                url = "https://s.1688.com/company/company_search.htm?keywords=" + search + "&n=y&netType=16&spm=a26352.13672862.suggestion.left.null&filt=y&beginPage=" + page + "#sm-filtbar";
                driver.get(url);
                document = Jsoup.parse(driver.getPageSource());
                addContacthrefList(document,contacthref);
            }
        }
        driver.close();
        for (Map<String,String>  map: contacthref) {
            BaiduGetinfoEntity temp = new BaiduGetinfoEntity();
            temp.setGetId(b.getId());
            temp.setInfoUrl(map.get("href"));
            temp.setTitle(map.get("title"));
            temp.setCompany(map.get("title"));
            temp.setSearchKey(b.getSearchKey());
            list.add(temp);
        }
        return list;
    }

    private List<BaiduGetinfoEntity> getBaiDuMessageAdvert( BaiduGetEntity b) throws IOException, InterruptedException {
        String search = URLEncoder.encode(b.getSearchKey(), "GBK");
        String province = URLEncoder.encode(b.getProvince(), "GBK");
        String city = URLEncoder.encode(b.getCity(), "GBK");
        List<Map<String,String>> contacthref = new ArrayList<>();
        List<BaiduGetinfoEntity> list = new ArrayList<>();
        int page = 1;
        String url = "https://s.1688.com/selloffer/offer_search.htm?keywords=" + search + "&button_click=top&n=y&netType=1%2C11&filt=y&beginPage=" + page + "#sm-filtbar";
        System.setProperty("webdriver.chrome.driver", "D:\\chrome\\chromedriver.exe");
        ChromeDriverService chromeDrService = ChromeDriverService.createDefaultService();
        WebDriver driver = new ChromeDriver(chromeDrService);
        driver.get(url);
        Document document = Jsoup.parse(driver.getPageSource());
        addContacthrefListAdvert(document,contacthref);
        if(document.select("em.fui-paging-num").first()!=null){
            String totalpage = document.select("em.fui-paging-num").first().text();
            int pagesize = totalpage!=null?Integer.parseInt(totalpage):1;
            if(pagesize>6){
                pagesize =7;
            }
            for (int i = 2; i <=pagesize; i++) {
                page = i;
                Thread.sleep(10*1000);
                url = "https://s.1688.com/selloffer/offer_search.htm?keywords=" + search + "&button_click=top&n=y&netType=1%2C11&filt=y&province="+province+"&city="+city+"&beginPage=" + page + "#sm-filtbar";
                driver.get(url);
                document = Jsoup.parse(driver.getPageSource());
                addContacthrefListAdvert(document,contacthref);
            }
        }
        driver.close();
        for (Map<String,String>  map: contacthref) {
            BaiduGetinfoEntity temp = new BaiduGetinfoEntity();
            temp.setGetId(b.getId());
            temp.setInfoUrl(map.get("href"));
            temp.setTitle(map.get("title"));
            temp.setCompany(map.get("title"));
            temp.setSearchKey(b.getSearchKey());
            list.add(temp);
        }
        return list;
    }

    @Override
    public void saveBaiDuMessages() {
        try {
            List<BaiduGetEntity> baiduGetEntities = baiduGetDao.selectList(new QueryWrapper<BaiduGetEntity>().eq("get_status", 0));
            if (baiduGetEntities.size() != 0) {
                for (BaiduGetEntity b : baiduGetEntities) {
                    Thread.sleep(10*1000);
                    List<BaiduGetinfoEntity> messageList = new ArrayList<>();
                    if("0".equals(b.getType())){
                        messageList = getBaiDuMessage( b);
                    }
                    if("1".equals(b.getType())){
                        messageList = getBaiDuMessageAdvert( b);
                    }
                    long l = System.currentTimeMillis();
                    String excelName = b.getSearchKey() + "_" + l;
                    b.setGetStatus(2);
                    baiduGetDao.updateById(b);
                    saveBatch(messageList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addContacthrefList(Document document,List<Map<String,String>> contacthref){
        Elements links = document.select("div.company-left-card");
        for (Element e : links) {
            Element  company_tag_row=e.selectFirst("div.company-tag-row");
            Element  company_name =e.selectFirst("a.company-name");
            String href = company_tag_row.select("a").first().attr("href");
            String title = company_name.text();
            if (href != null && href.indexOf("shili.1688.com") != -1) {
                href = e.select("a").next().attr("href");
            }
            Map<String,String> temp = new HashMap<>();
            temp.put("href",href.split("1688.com")[0] + "1688.com/page/contactinfo.htm");
            temp.put("title",title);
            contacthref.add(temp);
        }
    }

    private void addContacthrefListAdvert(Document document,List<Map<String,String>> contacthref){
        Elements links = document.select("div.common-offer-card").select(".ad-item");
        for (Element e : links) {
            Element  company_name =e.selectFirst("div.company-name");
            Element  a = company_name.selectFirst("a");
            String href = a.attr("href");
            String title = a.text();
            Map<String,String> temp = new HashMap<>();
            temp.put("href",href.split("1688.com")[0] + "1688.com/page/contactinfo.htm");
            temp.put("title",title);
            contacthref.add(temp);
        }
    }

}

package io.renren.modules.sys.util;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverUtils {

    public static void main(String args[]) {
        System.setProperty("webdriver.chrome.driver", "D:\\chrome\\chromedriver.exe");
        ChromeDriverService chromeDrService = ChromeDriverService.createDefaultService();
        WebDriver driver = new ChromeDriver(chromeDrService);
        driver.manage().window().maximize();
        login(driver);
        System.out.println(getContactInfo(driver, "https://shop2808475f8rt56.1688.com/page/contactinfo.htm"));
        System.out.println(getContactInfo(driver, "https://hchmed.1688.com/page/contactinfo.htm"));

    }

    public static String totalPage(WebDriver driver){
        Document document = Jsoup.parse(driver.getPageSource());
        String totalpage= document.select("em.fui-paging-num").first().text();
        return totalpage;
    }
    public static Elements getPageElements(WebDriver driver, String url) {
        driver.get(
                "https://s.1688.com/company/company_search.htm?keywords=%BB%AF%D7%B1%C6%B7&n=y&netType=16&spm=a26352.13672862.suggestion.left.null&filt=y&province=%E5%B1%B1%E4%B8%9C&city=%E6%B5%8E%E5%AE%81&beginPage=2#sm-filtbar");
        // 得到浏览器的标题
        System.out.println(driver.getTitle());
        System.out.println(driver.getPageSource());
        Document document = Jsoup.parse(driver.getPageSource());
        Elements links = document.select("div.next-slick-track");
        return links;
    }

    public static String getContactHref(WebDriver driver, String product_href) {
        driver.get(product_href);
        Document document = Jsoup.parse(driver.getPageSource());
        Element link = document.getElementsByAttributeValue("data-page-name", "contactinfo").first();
        String contactHref = link.select("a").first().attr("href");
        return contactHref;
    }

    public static void login(WebDriver driver) {
        driver.get("https://login.1688.com/member/signin.htm");
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Map<String, String> getContactInfo(WebDriver driver, String contactHref) {
        Map<String, String> result = new HashMap<String, String>();
        driver.get(contactHref);
        Document document = Jsoup.parse(driver.getPageSource());
        Element link = document.select("div.contact-info").first();
        String companyName = link.select("h4").first().text();
        String comtactName = link.select("a.membername").first().text();
        Element link2 = document.select("dl.m-mobilephone").first();
        String mobile = link2.select("dd").text();
        System.out.println(companyName + "||||||" + comtactName + "||||||" + mobile);
        return result;
    }
}

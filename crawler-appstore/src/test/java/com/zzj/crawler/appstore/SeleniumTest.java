package com.zzj.crawler.appstore;

import cn.hutool.core.collection.CollUtil;
import com.zzj.crawler.appstore.data.model.dao.Appinfo;
import com.zzj.crawler.appstore.repo.AppinfoRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerAppStoreApplication.class)
public class SeleniumTest {
    @Resource
    AppinfoRepo appinfoRepo;

    @Test
    public void test() {
        List<Appinfo> list = appinfoRepo.list();
        List<String> urls = list.stream().filter(k -> k.getUrl().contains("/charts/")) //
                .map(k -> k.getUrl()).collect(Collectors.toList());


//        List<String> urlList = Lists.newArrayList();
//        for (int i = 1; i < 3; i++) {
//            urlList.add(urls.get(i));
//        }
//        openUrlsInTabs(urlList);


                    for (String s : urls) {
                    System.out.println(s);
                }




    }

    private static ChromeDriver buildChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/xingchuan/framework/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("103");
        //        options.addArguments("--headless"); // 启用无头模式
        //        options.addArguments("--disable-gpu");

        ChromeDriver driver = new ChromeDriver(options);
        return driver;
    }

    public static void openUrlsInTabs(List<String> urls) {
        if (CollUtil.isEmpty(urls)) return;

        ChromeDriver driver = buildChromeDriver();
        driver.get(urls.get(0)); // 打开第一个URL在主标签

        // 为剩余URLs打开新标签
        for (int i = 1; i < urls.size(); i++) {
            ((JavascriptExecutor) driver).executeScript("window.open()");
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            if (tabs.size() > i) {
                driver.switchTo().window(tabs.get(i));
                driver.get(urls.get(i));
            } else {
                System.err.println("无法打开第 " + (i + 1) + " 个标签页");
            }
        }

        // 切换回第一个标签
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(0));
    }
}

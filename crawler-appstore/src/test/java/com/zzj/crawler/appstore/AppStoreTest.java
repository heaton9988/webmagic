package com.zzj.crawler.appstore;

import com.google.common.collect.Lists;
import com.zzj.crawler.appstore.webmagic.AppStorePageProcessor;
import com.zzj.crawler.appstore.webmagic.AppStorePipeline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.model.OOSpider;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerAppStoreApplication.class)
public class AppStoreTest {
    @Resource
    private AppStorePipeline appStorePipeline;
    @Resource
    AppStorePageProcessor appStorePageProcessor;

    @Test
    public void test() {
        System.setProperty("selenuim_config", "/Users/xingchuan/Desktop/self/webmagic/crawler-appstore/src/main/resources/config.ini");
        System.setProperty("webdriver.chrome.driver", "/Users/xingchuan/framework/chromedriver");

        OOSpider.create(appStorePageProcessor) //
                .setPipelines(Lists.newArrayList(appStorePipeline)) //
                .setDownloader(new SeleniumDownloader()) //
                .addUrl( //
                        "https://apps.apple.com/cn/charts/iphone/%E7%AD%96%E7%95%A5-games/7017" //
                        , "https://apps.apple.com/cn/charts/ipad" // ipad排行榜
                        , "https://apps.apple.com/cn/charts/iphone" // iphone排行榜
                ) //
                .thread(1) //
                .run();
    }
}

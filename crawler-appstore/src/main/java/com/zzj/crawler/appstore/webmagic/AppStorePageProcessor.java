package com.zzj.crawler.appstore.webmagic;


import cn.hutool.core.collection.CollUtil;
import com.zzj.crawler.appstore.data.model.dao.Appinfo;
import com.zzj.crawler.appstore.repo.AppinfoRepo;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AppStorePageProcessor implements PageProcessor {
    @Resource
    AppinfoRepo appinfoRepo;

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
//        List<String> resultsList = page.getHtml().xpath("//div[@data-testid='results-list']").all();
        List<String> resultsList = page.getHtml().xpath("//a[@class='we-genre-filter__item']").links().all();
        if (CollUtil.isNotEmpty(resultsList)) {
            // 这是列表页
            List<String> all = page.getHtml().xpath("").links().all();
            for (String s : all) {
                Appinfo appinfoEntity=new Appinfo();
                appinfoEntity.setUrl(s);
                appinfoRepo.save(appinfoEntity);
                System.out.println("zzj: " + s);
            }
        } else {

        }
        //        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        //        if (page.getResultItems().get("name") == null) {
        //            //skip this page
        //            page.setSkip(true);
        //        }
        //        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 部分三：从页面发现后续的url地址来抓取
        //        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000) //
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");
    }
}

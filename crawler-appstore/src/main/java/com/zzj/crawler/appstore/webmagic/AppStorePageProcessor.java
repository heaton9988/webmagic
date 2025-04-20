package com.zzj.crawler.appstore.webmagic;


import com.beust.jcommander.internal.Lists;
import com.zzj.crawler.appstore.data.model.dao.Appinfo;
import com.zzj.crawler.appstore.service.AppinfoService;
import com.zzj.crawler.appstore.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class AppStorePageProcessor implements PageProcessor {
    @Resource
    AppinfoService appinfoService;

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        Selectable pageUrlObj = page.getUrl();
        Html html = page.getHtml();

        String pageUrl = pageUrlObj.toString();

        appinfoService.saveOrUpdate(Appinfo.builder().url(pageUrl).objHtml(GsonUtil.serialize(html)).build());

        List<String> resultUrlList = html.links().all();
        for (String resultUrl : resultUrlList) {
            if (resultUrl.contains("/charts/") || resultUrl.contains("/app/")) {
                boolean saveResult = appinfoService.save(Appinfo.builder().url(resultUrl).parentUrl(pageUrl).build());
                if (saveResult) {
                    page.addTargetRequests(Lists.newArrayList(resultUrl));
                }
            } else {
                log.warn("resultUrl ignored: " + resultUrl);
            }
        }

        //        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        //        if (page.getResultItems().get("name") == null) {
        //            //skip this page
        //            page.setSkip(true);
        //        }

        // 部分三：从页面发现后续的url地址来抓取
    }


    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000) //
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");
    }
}

package com.zzj.crawler.appstore;

import cn.hutool.core.util.ReUtil;
import com.google.common.collect.Maps;
import com.zzj.crawler.appstore.data.model.dao.Appinfo;
import com.zzj.crawler.appstore.repo.AppinfoRepo;
import com.zzj.crawler.appstore.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerAppStoreApplication.class)
public class ParseObjHtmlTest {
    @Resource
    AppinfoRepo appinfoRepo;

    @Test
    public void test() throws UnsupportedEncodingException {
        List<Appinfo> list = appinfoRepo.lambdaQuery() //
                .isNotNull(Appinfo::getObjHtml) //
                .like(Appinfo::getUrl, "/app/") //
                .last("limit 10").list();
        for (Appinfo appinfo : list) {
            System.out.println(appinfo.getUrl());
            String url = appinfo.getUrl();

            String appCodeInUrl = URLDecoder.decode(ReUtil.extractMulti("https.*/app/(.*)", url, "$1"), "UTF-8");
            System.out.println(appCodeInUrl); // 存字段url_app_code

            String objHtml = appinfo.getObjHtml();
            Html html = GsonUtil.deserialize(objHtml, Html.class);

            Selectable selectableInformationList = html.xpath("//*[@class='information-list']");
            if (selectableInformationList.all().size() == 0) {
                log.error("找不到 [信息] 页块: {}", url);
                continue;
            }

            List<String> dtList = selectableInformationList.xpath("//dt").all();
            List<String> labelList = dtList.stream().map(k -> k.replaceAll("<.*?>", "").trim()).collect(Collectors.toList());

            List<String> ddList = selectableInformationList.xpath("//dd").all();
            List<String> valueList = ddList.stream().map(k -> k.replaceAll("<.*?>", "").trim()).collect(Collectors.toList());

            Map<String, String> informationMap = Maps.newLinkedHashMap();
            for (int i = 0; i < labelList.size(); i++) {
                informationMap.put(labelList.get(i), valueList.get(i));
            }
            System.out.println();
        }
    }
}

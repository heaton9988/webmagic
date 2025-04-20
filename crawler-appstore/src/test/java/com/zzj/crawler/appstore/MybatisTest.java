package com.zzj.crawler.appstore;

import cn.hutool.core.util.StrUtil;
import com.zzj.crawler.appstore.data.model.dao.Appinfo;
import com.zzj.crawler.appstore.repo.AppinfoRepo;
import com.zzj.crawler.appstore.util.GsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.selector.Html;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerAppStoreApplication.class)
public class MybatisTest {
    @Resource
    AppinfoRepo appinfoRepo;

    @Test
    public void test() {
        for (Appinfo appinfo : appinfoRepo.list()) {
            if (StrUtil.isNotBlank(appinfo.getObjHtml())) {
                String objHtml = appinfo.getObjHtml();
                Html deserialize = GsonUtil.deserialize(objHtml, Html.class);
                System.out.println("验证能否从数据库读取并解析成Html对象"); // 结论: 可以
            }
        }
    }
}

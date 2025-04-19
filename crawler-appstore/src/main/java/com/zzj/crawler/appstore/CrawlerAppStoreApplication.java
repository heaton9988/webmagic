package com.zzj.crawler.appstore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@Slf4j
//@MapperScan(basePackages = {"com.hthx.org.provider.data.mapper","com.hthx.dept2.integration.check.tenant.mapper"})
@SpringBootApplication(scanBasePackages = {"com"})
@EnableAsync
public class CrawlerAppStoreApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CrawlerAppStoreApplication.class);
        springApplication.setAllowCircularReferences(Boolean.TRUE);
        springApplication.run(args);
        log.info("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}

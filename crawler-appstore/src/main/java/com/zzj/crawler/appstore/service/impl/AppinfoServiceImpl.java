package com.zzj.crawler.appstore.service.impl;

import com.zzj.crawler.appstore.data.model.dao.Appinfo;
import com.zzj.crawler.appstore.repo.AppinfoRepo;
import com.zzj.crawler.appstore.service.AppinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppinfoServiceImpl implements AppinfoService {
    @Resource
    private AppinfoRepo appinfoRepo;

    @Override
    public List<Appinfo> queryAll() throws Exception {
        return appinfoRepo.list();
    }
}

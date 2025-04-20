package com.zzj.crawler.appstore.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.zzj.crawler.appstore.data.model.dao.Appinfo;
import com.zzj.crawler.appstore.repo.AppinfoRepo;
import com.zzj.crawler.appstore.service.AppinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class AppinfoServiceImpl implements AppinfoService {
    @Resource
    private AppinfoRepo appinfoRepo;

    @Override
    public List<Appinfo> queryAll() throws Exception {
        return appinfoRepo.list();
    }

    @Override
    public boolean save(Appinfo entity) {
        try {
            boolean result = appinfoRepo.save(entity);
            return result;
        } catch (Exception e) {
            String errMsg = e.getMessage();
            if (errMsg.contains("Duplicate entry")) {
                log.warn(errMsg);
            } else {
                log.error("save", e);
            }
            return false;
        }
    }

    @Override
    public boolean saveOrUpdate(Appinfo entity) {
        try {
            List<Appinfo> list = appinfoRepo.lambdaQuery().eq(Appinfo::getUrl, entity.getUrl()).list();
            if (CollUtil.isEmpty(list)) {
                return appinfoRepo.save(entity);
            } else {
                entity.setId(list.get(0).getId());
                return appinfoRepo.updateById(entity);
            }
        } catch (Exception e) {
            log.error("saveOrUpdate", e);
            return false;
        }
    }
}

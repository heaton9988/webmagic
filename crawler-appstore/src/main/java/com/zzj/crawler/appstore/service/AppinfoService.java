package com.zzj.crawler.appstore.service;


import com.zzj.crawler.appstore.data.model.dao.Appinfo;

import java.util.List;

/**
 * @author : zhijun.zhou
 */
public interface AppinfoService {
    List<Appinfo> queryAll() throws Exception;
}

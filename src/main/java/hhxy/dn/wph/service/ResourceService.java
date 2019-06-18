package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.Resource;

/**
 * @Author: 邓宁
 * @Date: Created in 15:25 2019/6/13
 */

public interface ResourceService {

    Resource findResourceByUrl(String url);
}

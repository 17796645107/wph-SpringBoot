package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.domain.Resource;
import hhxy.dn.wph.mapper.ResourceMapper;
import hhxy.dn.wph.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 邓宁
 * @Date: Created in 15:26 2019/6/13
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Resource findResourceByUrl(String url) {
        return resourceMapper.findResourceByUrl(url);
    }
}

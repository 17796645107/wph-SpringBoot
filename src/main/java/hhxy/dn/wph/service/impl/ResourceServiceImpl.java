package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.entity.Resource;
import hhxy.dn.wph.enums.GeneralExceptionEnum;
import hhxy.dn.wph.exception.GeneralException;
import hhxy.dn.wph.mapper.ResourceMapper;
import hhxy.dn.wph.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 15:26 2019/6/13
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Resource getResourceByUrl(String url) {
        List<Resource> resourceList = resourceMapper.listResource();
        //结果为空,抛出NOT_FOUND异常
        if (resourceList.isEmpty()){
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND);
        }

        //判断URL前缀是否一致
        // eg:/user/getUserById/*  替换之后的字符串 /user/getUserById/
        //    /user/getUserById/{userId}
        String urlTemp = "";
        for (Resource resource : resourceList) {
            urlTemp = resource.getUrl();
            urlTemp = urlTemp.replace("*","");
            if (url.startsWith(urlTemp)){
                return resource;
            }
        }
        return null;
    }
}

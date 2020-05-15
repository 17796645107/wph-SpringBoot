package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 邓宁
 * @date Created in 12:14 2018/12/7
 */

public interface MenuService {
    List<Menu> getAllMenu();
}

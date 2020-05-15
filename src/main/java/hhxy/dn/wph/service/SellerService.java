package hhxy.dn.wph.service;

import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Product;
import hhxy.dn.wph.entity.SellerAccount;
import hhxy.dn.wph.entity.Seller;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 邓宁
 * @date Created in 16:27 2019/4/7
 */

public interface SellerService {

    /**
     * 查询商户列表
     * @param categoryId 分类ID
     * @return java.util.List<hhxy.dn.wph.entity.Seller>
     */
    List<Seller> listSellerByCategoryId(Integer categoryId);

    /**
     * 根据编号获取商户信息
     * @param sellerId
     * @return hhxy.dn.wph.entity.Seller
     */
    Seller getSellerById(Integer sellerId);

    /**
     * 获取商户收藏量
     * @param sellerId
     * @return int
     */
    int getSellerCollectNum(Integer sellerId);

    /**
     * 商户登录
     * @param sellerAccount
     * @return hhxy.dn.wph.entity.Seller
     */
    Seller login(SellerAccount sellerAccount);

    /**
     *
     * @param sellerId
     * @param pageIndex
     * @param pageCount
     * @return PageInfo
     */
    PageInfo pageListProductById(int sellerId,int pageIndex, int pageCount);

    /**
     *  @param product
     * @param file
     * @param colors
     * @param sizes
     */
    void addProduct(Product product, MultipartFile[] file, String[] colors, String[] sizes);

    void updateProduct(int productId);
}

package hhxy.dn.wph.enums;

/**
 * @Author: 邓宁
 * @Date: Created in 16:12 2018/11/9
 * 商品异常信息枚举
 */
public enum ProductExceptionEnum {

    //异常枚举
    FIND_PRODUCT_BY_PARENTID_ERROR(4001,"根据二级分类ID查找商品失败:null"),
    FIND_HOTMANWEAR_ERROR(4002,"查询热门男装失败:null"),
    FIND_PRIMARY_ERROR(4003,"查询一级商品分类目录失败:null"),
    FIND_PRODUCT_SIZE_ERROR(4004,"查询所有商品尺寸失败:null"),
    FIND_SECONDARTID_BY_PARENTID_ERROR(4005,"根据一级分类ID查询二级分类ID失败:null"),
    FIND_PRODUCTID_BY_PARENTID_ERROR(4006,"根据二级分类ID查询商品ID:null"),
    FIND_PRODUCT_BY_PRODUCTID_ERROR(4007,"根据商品ID查询商品失败:null"),
    FIND_PRODUCT_NUM_COLOR_ERROR(4008,"请选择商品颜色"),
    FIND_PRODUCT_NUM_SIZE_ERROR(4009,"请选择商品尺寸"),
    PRODUCT_NUM_ERROR(4010,"库存不足"),
    FIND_SECONDARY_ERROR(4011,"查询商品二级目录失败:null"),
    UPDATE_PRODUCT_NUM_ERROR(4022,"更新商品库存失败")
    ;

    private Integer code;
    private String msg;

    ProductExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

package hhxy.dn.wph.util;

import hhxy.dn.wph.enums.UserExceptionEnum;
import hhxy.dn.wph.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 文件上传工具类
 * @author 邓宁
 * @date Created in 13:52 2019/5/12
 */

public class UploadFileUtil {

    /**
     * 用户头像保存路径
     */
    private final static String userHeadPath = "D:\\JetBrains\\workspace\\wph-vue\\static\\user\\headIcon\\";
    /**
     * 用户头像保存路径
     */
    private final static String productImagePath = "D:\\JetBrains\\workspace\\wph-vue\\static\\product\\";
    /**
     * 日志
     */
    private static final Logger LOGGER= LoggerFactory.getLogger(UploadFileUtil.class);

    /**
     * 上传用户头像
     * @param file 图片文件
     */
    public static void uploadUserIcon(MultipartFile file){
        if (Objects.isNull(file) || file.isEmpty()){
            throw new UserException(UserExceptionEnum.NULL_FILE_ERROR);
        }
        //上传图片
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(userHeadPath + file.getOriginalFilename());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(userHeadPath));
            }
            //文件写入指定路径
            Files.write(path, bytes);
        }catch (IOException e){
            LOGGER.error("文件上传异常",e);
            throw new UserException(UserExceptionEnum.UPLOAD_FILE_ERROR);
        }
    }
    /**
     * 上传用户头像
     * @param file 图片文件
     */
    public static void uploadProductImage(MultipartFile file){
        if (Objects.isNull(file) || file.isEmpty()){
            throw new UserException(UserExceptionEnum.NULL_FILE_ERROR);
        }
        //上传图片
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(productImagePath + file.getOriginalFilename());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(productImagePath));
            }
            //文件写入指定路径
            Files.write(path, bytes);
        }catch (IOException e){
            LOGGER.error("文件上传异常",e);
            throw new UserException(UserExceptionEnum.UPLOAD_FILE_ERROR);
        }
    }
    /**
     * 上传图片
     * @param file 图片文件
     */
    public static void uploadFiles(MultipartFile[] file){
        for (MultipartFile multipartFile : file) {
            uploadProductImage(multipartFile);
        }
    }
}

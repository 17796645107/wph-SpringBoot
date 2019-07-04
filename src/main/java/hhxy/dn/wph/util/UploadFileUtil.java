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
 * @Author: 邓宁
 * @Date: Created in 13:52 2019/5/12
 */

public class UploadFileUtil {

    private static final Logger LOGGER= LoggerFactory.getLogger(UploadFileUtil.class);

    public static void uploadFile(MultipartFile file,String url){
        if (Objects.isNull(file) || file.isEmpty()){
            throw new UserException(UserExceptionEnum.CODE_ERROR);
        }
        //上传图片
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(url + file.getOriginalFilename());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(url));
            }
            //文件写入指定路径
            Files.write(path, bytes);
        }catch (IOException e){
            LOGGER.error("文件上传异常={}",e);
            throw new UserException(UserExceptionEnum.UPLOAD_FILE_ERROR);
        }
    }
}

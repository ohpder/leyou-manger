package cn.leyou.upload.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {
    private static final List<String> IMAGE_TYPES = Arrays.asList("image/gif","image/jpg","image/jpeg");

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);
    public String uploadImage(MultipartFile multipartFile) {

        try {
            String filename = multipartFile.getOriginalFilename();
            //效验文件类型
            String filetype = multipartFile.getContentType();
            if (!IMAGE_TYPES.contains(filetype)){
                LOGGER.error("文件类型不合法：{}",filename);
                return null;
            }
            //效验文件内容
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if (bufferedImage==null){
                LOGGER.error("文件内容不合法：{}",filename);
                return null;
            }
            //保存文件到服务器
            multipartFile.transferTo(new File("F:\\leyou\\image\\"+filename));
            //返回url
            return "http://image.leyou.com/"+filename;
        } catch (IOException e) {
            LOGGER.error("服务器内部错误");
            e.printStackTrace();
            return null;
        }

    }
}

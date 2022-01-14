package com.zzguo;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * @Author zzguo
 * @Description 原生方法测试
 * @Date 2022/1/9

 **/
public class Compressing {
    public static void main(String[] args) {

        try {
            File input = new File("src/main/resources/HightDataImage.png");
            BufferedImage image = ImageIO.read(input);
            
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("png");
            ImageWriter writer = (ImageWriter) writers.next();

            File compressedImageFile = new File("HightDataImage.png");
            // 创建输出流
            OutputStream os = new FileOutputStream(compressedImageFile);
            // 创建输出流的图片对象
            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);

            // 图片参数配置
            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            // 保存质量参数
            param.setCompressionQuality(0.3f);

            writer.write(null, new IIOImage(image, null, null), param);

            os.close();
            ios.close();
            writer.dispose();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

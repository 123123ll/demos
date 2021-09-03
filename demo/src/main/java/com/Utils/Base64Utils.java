package com.Utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.http.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Base64 编码工具类
 *
 * @author Orange
 * @date 2021/3/11
 */
public class Base64Utils {

    /**
     * 将图片转换为Base64字符串
     *
     * @param filePath 文件路径
     * @return encode Base64字符串
     * @author Orange
     * @date 2021/3/11
     */
    public static String GetImageStr(String filePath) {
        String encode = null;
        InputStream in = null;
        try {
            in = new FileInputStream(filePath);
            encode = GetImageStr(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert in != null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encode;
    }

    /**
     * 将图片转换为Base64字符串
     *
     * @param fileInput 文件输入流
     * @return encode 图片Base64 字符串
     * @author Orange
     * @date 2021/3/11
     */
    public static String GetImageStr(InputStream fileInput) {
        InputStream in = null;
        byte[] data = null;
        String encode = null;
        BASE64Encoder base64Encoder = new BASE64Encoder();

        try {
            in = fileInput;
            data = new byte[in.available()];

//            //判断文件大小
//            double size = data.length / (1024.00 * 1024.00); //
//            if (size > 2) {
//                throw new RuntimeException("图片最大2M");
//            }
            in.read(data);
            encode = base64Encoder.encode(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert in != null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encode;
    }

    /**
     * @param imgEncode 图片Base64 编码字符串
     * @param filePath  需要转换的地址
     * @return boolean true: 成功转换，false：转换失败
     * @author Orange
     * @date 2021/3/11
     */
    public static boolean GenerateImage(String imgEncode, String filePath) {
        if (StringUtils.isEmpty(imgEncode) || StringUtils.isEmpty(filePath)) {
            return false;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;

        try {
            out = new FileOutputStream(filePath);
            byte[] bytes = decoder.decodeBuffer(imgEncode);
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 0) {
                    bytes[i] += 256; //调整异常数据
                }
            }
            out.write(bytes);

        } catch (IOException e) {
            return false;
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    public static String urlGenerateImage(String imageUrl) {
        return null;
    }

    /**
     * url转变为 MultipartFile对象
     * @param url
     * @param fileName
     * @return
     * @throws Exception
     */
    public static MultipartFile createFileItem(String url, String fileName) throws Exception{
        FileItem item = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            //设置应用程序要从网络连接读取数据
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();

                FileItemFactory factory = new DiskFileItemFactory(16, null);
                String textFieldName = "uploadfile.jpg";
                item = factory.createItem(textFieldName, ContentType.APPLICATION_OCTET_STREAM.toString(), false, fileName);
                OutputStream os = item.getOutputStream();
                System.out.println("旧："+item.getSize());
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                System.out.println("新："+item.getSize());
                os.close();
                is.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("文件下载失败", e);
        }

        return new CommonsMultipartFile(item);
    }

    public static void main(String[] args) throws Exception{
        String picUrl = "https://fastgw-ali.ys7.com/1/capture/2021/4/9/1di65nwry72aiyvp1gvu6fjsw.jpg?Expires=1618041537&OSSAccessKeyId=LTAIzI38nEHqg64n&Signature=%2FEY95ewfu0nEhhG%2F91HcVyefgsw%3D&bucket=ezviz-fastdfs-gateway";
        MultipartFile fileItem = Base64Utils.createFileItem(picUrl, 1111 + ".jpg");
        System.out.println(fileItem.getResource());

//        String encode = GetImageStr("E:\\Downloads\\C1A6D3EE-A61B-4331-9B27-81B5EAEB270F.png");
//        System.out.println(encode);
//        String url = "https://fastgw-ali.ys7.com/1/capture/2021/3/15/3cdny0kvpo8dajym1n9opx4w.jpg?Expires=1615883990&OSSAccessKeyId=LTAIzI38nEHqg64n&Signature=8j5%2FHmG7f95qal9hWhhMGJ%2FQpGU%3D&bucket=ezviz-fastdfs-gateway";
//        try {
//            System.out.println(download(url));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

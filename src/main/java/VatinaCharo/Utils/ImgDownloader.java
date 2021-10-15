package VatinaCharo.Utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImgDownloader {
    /**
     * @param url       图片下载地址
     * @param imagePath 图片存放地址
     * @param id        task id
     * @return file name
     * @throws Exception 下载错误或文件读写错误
     */
    public static String download(URL url, String imagePath, int id) throws Exception {
        String fileName = System.currentTimeMillis() + "_" + id + ".jpg";
        //建立网络链接
        URLConnection uc =  url.openConnection();
        //设置超时时限为30s
        uc.setConnectTimeout(30_000);
        //获取网络输入流
        InputStream in = uc.getInputStream();
        FileOutputStream out = new FileOutputStream(imagePath + fileName);
        for (int imgByte; (imgByte = in.read()) != -1; ) {
            out.write(imgByte);
        }
        in.close();
        out.close();
        return fileName;
    }

    public static String download(String url, String imagePath) throws Exception {
        return download(new URL(url), imagePath, 0);
    }
}
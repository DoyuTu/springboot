package com.doyutu.springbootspider.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

public class HttpUtils {

    /**
     * @param url
     * @return
     * @Description http发送get请求返回InputStream
     * @author zhangyd
     * @date 2015年12月4日 上午11:11:15
     */
    public static InputStream httpRequestByGetIn(String url) {
        // 创建get
        HttpGet httpGet = new HttpGet(url);
        // 设置通用的请求属性
        httpGet.setHeader("accept", "*/*");
        httpGet.setHeader("connection", "Keep-Alive");
        httpGet.setHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        InputStream in = null;
        try {
            // 执行http请求
            response = httpclient.execute(httpGet);
            // 获取返回实体流
            in = response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回
        return in;
    }

    /**
     * @param url
     * @return
     * @Description http发送get请求返回String字符串
     * @author zhangyd
     * @date 2015年12月4日 上午11:11:15
     */
    public static String httpRequestByGetStr(String url) {
        try {
            return getResultContent(httpRequestByGetIn(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param url
     * @param responseBody
     * @param @param       responseBody
     * @return
     * @throws IOException
     * @Description http发送post请求返回InputStream
     * @author zhangyd-c
     * @date 2015年8月13日 下午5:48:19
     */
    @SuppressWarnings("resource")
    public static InputStream httpRequestByPostIn(String url, String responseBody, String signupKey) {
        // 创建http请求、设置请求属性
        HttpPost post = new HttpPost(url);
        // 设置通用的请求属性
        post.setHeader("accept", "*/*");
        post.setHeader("connection", "Keep-Alive");
        post.setHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("Signup-Key", signupKey);
        post.setEntity(new StringEntity(responseBody, "utf-8"));
        // 发起请求
        HttpResponse responses;
        try {
            responses = new DefaultHttpClient().execute(post);
            return responses.getEntity().getContent();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param url
     * @param responseBody
     * @param @param       responseBody
     * @return
     * @throws IOException
     * @Description http发送post请求返回String
     * @author zhangyd-c
     * @date 2015年8月13日 下午5:48:19
     */
    public static String httpRequestByPostStr(String url, String responseBody, String signupKey) {
        try {
            return getResultContent(httpRequestByPostIn(url, responseBody, signupKey));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param inStream
     * @return
     * @throws Exception
     * @Description 读取流
     * @author zhangyd-c
     * @date 2015年8月13日 上午9:51:34
     */
    public static byte[] getInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        return outStream.toByteArray();
    }

    /**
     * @param in
     * @return
     * @throws IOException
     * @Description 获取InputStream流中的内容
     * @author zhangyd-c
     * @date 2015年8月13日 上午11:05:31
     */
    public static String getResultContent(InputStream in) throws IOException {
        String result = "";
        if (null != in) {
            StringBuffer content = null;
            // 获取返回实体
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            content = new StringBuffer();
            String line = "";
            while ((line = r.readLine()) != null) {
                content.append(line);
            }
            result = content.toString();
        }
        return result;
    }

    /**
     * @param closeables
     * @Description 关闭资源(多关闭)
     * @author zhangyd
     * @date 2015年12月9日 下午1:39:12
     */
    public static void close(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String getURLName(String url) {
        int idx = url.lastIndexOf("/");
        idx = idx == -1 ? url.length() : idx;
        return url.substring(idx + 1);
    }

    public static void main(String[] args) {
        System.out.println(getURLName("http://static.runoob.com/images/icon/html5.png"));
    }

    public static void createPng(String localPath, String url) {
        System.out.println("download image in:" + url);
        InputStream is = httpRequestByGetIn(url);
        FileOutputStream fos = null;
        try {
            File file = new File(localPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            int bytesWritten = 0;
            int byteCount = 0;
            fos = new FileOutputStream(localPath + "\\" + getURLName(url));
            byte[] b = new byte[1024];
            while ((byteCount = is.read(b)) != -1) {
                fos.write(b, bytesWritten, byteCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(is, fos);
        }

    }
}

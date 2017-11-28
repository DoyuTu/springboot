package com.doyutu.springbootspider.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static com.doyutu.springbootspider.NetEaseMusicUtils.aesEncrypt;
import static com.doyutu.springbootspider.NetEaseMusicUtils.rsaEncrypt;

public class SpiderPageProcessor implements PageProcessor {

    //正则表达式\\. \\转义java中的\  \.转义正则中的.

    //主域名
    public static final String BASE_URL = "http://music.163.com/";

    //匹配专辑URL
    public static final String ALBUM_URL = "http://music\\.163\\.com/album\\?id=\\d+";

    //匹配歌曲URL
    public static final String MUSIC_URL = "http://music\\.163\\.com/song\\?id=\\d+";

    //初始地址, JAY_CHOU 周杰伦的床边故事专辑, 可以改为其他歌手专辑地址
    public static final String START_URL = "http://music.163.com/album?id=34720827";

    //加密使用到的文本
    public static final String TEXT = "{\"username\": \"\", \"rememberLogin\": \"true\", \"password\": \"\"}";

    //爬取结果保存文件路径
    public static final String RESULT_PATH = "C:\\Users\\ADMIN\\Desktop\\GitBook\\web";

    private Site site = Site.me()
            .setDomain("http://music.163.com")
            .setSleepTime(1000)
            .setRetryTimes(30)
            .setCharset("utf-8")
            .setTimeOut(30000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        //根据URL判断页面类型
        if (page.getUrl().regex(ALBUM_URL).match()) {
            //爬取歌曲URl加入队列
            page.addTargetRequests(page.getHtml().xpath("//div[@id=\"song-list-pre-cache\"]").links().regex(MUSIC_URL).all());

            //爬取专辑URL加入队列
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"cver u-cover u-cover-3\"]").links().regex(ALBUM_URL).all());
        } else {
            String url = page.getUrl().toString();
            page.putField("title", page.getHtml().xpath("//em[@class='f-ff2']/text()"));
            page.putField("author", page.getHtml().xpath("//p[@class='des s-fc4']/span/a/text()"));
            page.putField("album", page.getHtml().xpath("//p[@class='des s-fc4']/a/text()"));
            page.putField("URL", url);

            //单独对AJAX请求获取评论数, 使用JSON解析返回结果
            page.putField("commentCount", JSONPath.eval(JSON.parse(crawlAjaxUrl(url.substring(url.indexOf("id=") + 3))), "$.total"));
        }
    }

    public static void main(String[] args) {
        Spider.create(new SpiderPageProcessor())
                //初始URL
                .addUrl(START_URL)
                .addPipeline(new ConsolePipeline())
                //结果输出到文件
//                .addPipeline(new FilePipeline(RESULT_PATH))
                .run();
    }

    //对AJAX数据进行单独请求
    public String crawlAjaxUrl(String songId) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response =null;

        try {
            //参数加密
            String secKey = new BigInteger(100, new SecureRandom()).toString(32).substring(0, 16);
            String encText = aesEncrypt(aesEncrypt(TEXT, "0CoJUm6Qyw8W8jud"), secKey);
            String encSecKey = rsaEncrypt();

            HttpPost httpPost = new HttpPost("http://music.163.com/weapi/v1/resource/comments/R_SO_4_" + songId + "/?csrf_token=");
            httpPost.addHeader("Referer", BASE_URL);

            List<NameValuePair> ls = new ArrayList<NameValuePair>();
            ls.add(new BasicNameValuePair("params", encText));
            ls.add(new BasicNameValuePair("encSecKey", encSecKey));

            UrlEncodedFormEntity paramEntity = new UrlEncodedFormEntity(ls, "utf-8");
            httpPost.setEntity(paramEntity);

            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return EntityUtils.toString(entity, "utf-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response!=null) {
                    response.close();
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }
}
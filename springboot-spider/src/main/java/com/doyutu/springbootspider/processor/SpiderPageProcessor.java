package com.doyutu.springbootspider.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class SpiderPageProcessor implements PageProcessor {

    private static final String BASE_URL = "http://music.163.com/";

    private static final String PLAY_LIST_URL = "http://music.163.com/#/playlist?id=60104482";
    // 匹配歌曲URL
    public static final String MUSIC_URL = "http://music\\.163\\.com/song\\?id=\\d+";

    private Site site = Site.me()
            .setDomain("http://music.163.com")
            .setCycleRetryTimes(5)
            .setRetryTimes(5)
            .setSleepTime(100)
            .setTimeOut(60 * 1000)
            .setCharset("utf-8");

    private static List<String> list = new ArrayList<>();

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(MUSIC_URL).match()) {
            System.out.println("歌曲总数----->" + page.getHtml().xpath("//span[@id='playlist-track-count']/text()").toString());
            // 爬取歌曲URl加入队列
            page.addTargetRequests(page.getHtml().xpath("//div[@id=\"song-list-pre-cache\"]").links().regex(MUSIC_URL).all());
        }
    }

    public static void main(String[] args) {
    }

    @Override
    public Site getSite() {
        return site;
    }
}
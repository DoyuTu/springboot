package com.doyutu.springbootspider.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class NeteaseMusicListProcessor implements PageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(NeteaseMusicListProcessor.class);

    private Site site = Site.me()
            .setCharset("utf-8")
            .setSleepTime(200)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
            .setDomain("Spider")
            .setRetryTimes(3)
            ;

    @Override
    public void process(Page page) {
        if (page.getUrl().regex("playlist").match()) {
            Selectable songList = page.getHtml().xpath("//a[contains(@href,'/song?id=')]");
            List<String> links = songList.links().all();
            page.addTargetRequests(links);
        } else {
            String artist = page.getHtml().xpath("//a[@class and @href][0]/text()").toString();
            String title = page.getHtml().xpath("//a[@class=\"f-ff2\"][0]/text()").toString();
            page.putField("artist", artist);
            page.putField("title", title);
            return;
        }

    }

    private static final String PLAYLIST_ID = "";

    public static void main(String[] args) {
        Spider.create(new NeteaseMusicListProcessor())
                .addUrl("http://music.163.com/playlist?id=" +  PLAYLIST_ID)
                .thread(1)
                .addPipeline(new ConsolePipeline())
                .start();
    }

    @Override
    public Site getSite() {
        return site;
    }
}

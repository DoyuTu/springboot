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
            .setDomain("http://music.163.com/")
            .setRetryTimes(3)
            ;

    @Override
    public void process(Page page) {
        Selectable selectable = page.getHtml().xpath("//a[@href]");
        List<String> regxList = selectable.regex("/song\\?id=\\d{1,11}").all();

        return;
    }

    public static void main(String[] args) {
        Spider.create(new NeteaseMusicListProcessor())
                .addUrl("http://music.163.com/playlist?id=")
                .addPipeline(new ConsolePipeline())
                .start();
    }

    @Override
    public Site getSite() {
        return site;
    }
}

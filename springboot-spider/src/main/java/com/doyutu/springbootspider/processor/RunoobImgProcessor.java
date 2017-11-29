package com.doyutu.springbootspider.processor;

import com.doyutu.springbootspider.utils.ImagePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class RunoobImgProcessor implements PageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(RunoobImgProcessor.class);

    private Site site = Site.me()
            .setDomain("http://www.runoob.com/")
            .setRetryTimes(3)
            .setSleepTime(300)
            .setCharset("utf-8")
            .setUserAgent("Spider");

    @Override
    public void process(Page page) {
        List<String> list = page.getHtml().regex("static.runoob.com\\/images\\/icon\\/\\w{0,10}.png").all();
        page.putField("list", list);
        logger.info(list.size() + "");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new RunoobImgProcessor())
                .addUrl("http://www.runoob.com")
                .thread(2)
                .setUUID("runoob")
                .setSpawnUrl(true)
                .addPipeline(new ConsolePipeline())
                .addPipeline(new ImagePipeline("C:\\Users\\win7\\Desktop\\GitBook\\png"))
                .start();
    }
}

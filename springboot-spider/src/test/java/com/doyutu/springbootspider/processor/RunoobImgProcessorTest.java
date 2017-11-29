package com.doyutu.springbootspider.processor;

import com.doyutu.springbootspider.utils.ImagePipeline;
import org.junit.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import static org.junit.Assert.*;

public class RunoobImgProcessorTest {

    /**
     * 将在 path + uuid 路径下生成图片
     * @throws InterruptedException
     */
    @Test
    public void run() throws InterruptedException {
        String path = "D:\\png";
        Spider.create(new RunoobImgProcessor())
                .addUrl("http://www.runoob.com")
                .thread(2)
                .setUUID("runoob")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new ImagePipeline(path))
                .start();
        Thread.sleep(20000L);
    }

}
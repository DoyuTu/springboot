package com.doyutu.springbootspider.utils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;

import java.util.List;

public class ImagePipeline extends FilePipeline {

    public ImagePipeline(String path) {
        super(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        path = this.path + "/" + task.getUUID();
        List<String> list = resultItems.get("list");
        for (String url : list) {
            HttpUtils.createPng(path, "http://" + url);
        }
    }
}

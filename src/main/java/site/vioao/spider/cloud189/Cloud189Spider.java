package site.vioao.spider.cloud189;

import site.vioao.spider.FilePersistPipeLine;
import site.vioao.spider.FileHelper;
import site.vioao.spider.cloud189.utils.Cloud189UrlUtil;
import site.vioao.spider.cookie.ManualCookieProducer;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

public class Cloud189Spider {
    private static final String PATH = "/Users/vioao/Documents/geektime";
    private static final String FILE_REGEX = ".*\\.pdf";

    public static void main(String[] args) {
        Cloud189ValidateParam validateParam = Cloud189ValidateParam.builder()
                .shortCode("yIbuMnUFjmYz").accessCode("c8hx").verifyCode("685350").build();
        FileHelper fileHelper = new FileHelper(PATH, FILE_REGEX);

        Spider spider = Spider.create(new Cloud189PageProcessor(validateParam, fileHelper, new ManualCookieProducer()));
        Scheduler scheduler = new FileCacheQueueScheduler(PATH);
        scheduler.push(new Request(Cloud189UrlUtil.getListDirUrl(validateParam)), spider);
        spider.setScheduler(scheduler)
                .addPipeline(new FilePersistPipeLine(fileHelper))
                .thread(10)
                .run();
    }
}

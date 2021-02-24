package site.vioao.spider.cloud189;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpHeaders;
import site.vioao.spider.Const;
import site.vioao.spider.FileHelper;
import site.vioao.spider.FileItem;
import site.vioao.spider.cloud189.model.Cloud189FileItem;
import site.vioao.spider.cloud189.model.Data;
import site.vioao.spider.cloud189.utils.Cloud189UrlUtil;
import site.vioao.spider.cookie.CookieProducer;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Cloud189PageProcessor implements PageProcessor {
    private final Cloud189ValidateParam validateParam;
    private final FileHelper fileHelper;
    private final CookieProducer cookieProducer;

    public Cloud189PageProcessor(Cloud189ValidateParam validateParam, FileHelper fileHelper, CookieProducer cookieProducer) {
        this.validateParam = validateParam;
        this.fileHelper = fileHelper;
        this.cookieProducer = cookieProducer;
    }

    private Site site = Site.me().setDomain("cloud.189.cn")
            // add header
            .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36")
            .addHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7")
            // set configuration
            .setRetryTimes(3)
            .setSleepTime(1000);

    @Override
    public void process(Page page) {
        String contentType = page.getHeaders().get(HttpHeaders.CONTENT_TYPE).get(0).split(";")[0];
        if ("application/octet-stream".equalsIgnoreCase(contentType)) {
            log.info("downloaded file from : " + page.getRequest().getUrl());
            FileItem item = page.getRequest().getExtra(Const.FILE_ITEM);
            item.setBytes(page.getBytes());
            page.putField(Const.FILE_ITEM, item);
        } else if ("application/json".equalsIgnoreCase(contentType)) {
            List<Request> urls = getAllValidUrls(page);
            for (Request url : urls) {
                page.addTargetRequest(url);
                log.debug("add new url: {}", url.getUrl());
            }
        } else {
            log.warn("unsupported contentType. type: {}", contentType);
        }
    }

    private List<Request> getAllValidUrls(Page page) {
        String response = page.getRawText();
        List<Request> urls = new ArrayList<>();
        if (response.startsWith("{")) {
            Cloud189FileItem item = JSON.parseObject(page.getRawText(), Cloud189FileItem.class);

            int pageNum = item.getPageNum();
            while (pageNum * item.getPageSize() < item.getRecordCount()) {
                pageNum++;
                String currentFileId = item.getPath().get(item.getPath().size() - 1).getFileId();
                String url = Cloud189UrlUtil.getListDirUrl(validateParam, currentFileId, pageNum, item.getPageSize());
                url = UrlUtils.canonicalizeUrl(url, page.getUrl().toString());
                urls.add(new Request(url));
            }

            if (CollectionUtils.isNotEmpty(item.getData())) {
                for (Data datum : item.getData()) {
                    if (datum.getIsFolder()) {
                        urls.add(new Request(Cloud189UrlUtil.getListDirUrl(validateParam, datum.getFileId())));
                    } else {
                        String path = Cloud189UrlUtil.getFilePath(item.getPath());
                        if (fileHelper.isValid(path, datum.getFileName())) {
                            String fileId = item.getPath().get(item.getPath().size() - 1).getFileId();
                            String url = Cloud189UrlUtil.getGetFileDownloadUrl(validateParam, fileId, datum.getFileId());
                            url = UrlUtils.canonicalizeUrl(url, page.getUrl().toString());
                            Request request = new Request(url);
                            Map<String, Object> map = new HashMap<>();
                            map.put(Const.FILE_ITEM, FileItem.builder()
                                    .fileName(datum.getFileName())
                                    .path(Cloud189UrlUtil.getFilePath(item.getPath()))
                                    .build());
                            request.setExtras(map);
                            urls.add(request);
                        } else {
                            log.warn("file exist. ignore. path: {}, name: {}", path, datum.getFileName());
                        }
                    }
                }
            }
        } else {
            String url = "https:" + response.replaceAll("\"", "").replaceAll("\\\\", "");
            url = UrlUtils.canonicalizeUrl(url, page.getUrl().toString());
            Request request = new Request(url);
            request.setExtras(page.getRequest().getExtras());
            urls.add(request);
        }

        return urls;
    }

    @Override
    public Site getSite() {
        for (Map.Entry<String, String> cookie : cookieProducer.produce().entrySet()) {
            site.addCookie(cookie.getKey(), cookie.getValue());
        }
        return site;
    }
}

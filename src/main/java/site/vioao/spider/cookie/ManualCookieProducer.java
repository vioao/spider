package site.vioao.spider.cookie;

import java.util.HashMap;
import java.util.Map;

public class ManualCookieProducer implements CookieProducer {

    @Override
    public Map<String, String> produce() {
        // todo: add cookie manually
        Map<String, String> cookies = new HashMap<>();
        return cookies;
    }
}

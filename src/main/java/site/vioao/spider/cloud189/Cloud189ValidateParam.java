package site.vioao.spider.cloud189;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cloud189ValidateParam {
    private final String shortCode;
    private final String accessCode;
    private final String verifyCode;
}

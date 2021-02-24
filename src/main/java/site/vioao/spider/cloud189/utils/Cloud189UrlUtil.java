package site.vioao.spider.cloud189.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import site.vioao.spider.cloud189.Cloud189ValidateParam;
import site.vioao.spider.cloud189.model.Path;

import java.io.File;
import java.util.List;

public class Cloud189UrlUtil {
    private static final String LIST_DIR_URL = "https://cloud.189.cn/v2/listShareDirByShareIdAndFileId.action?" +
            "shortCode=%s&accessCode=%s&verifyCode=%s&pageNum=%d&pageSize=%d";

    private static final String GET_FILE_DOWNLOAD_URL = "https://cloud.189.cn/v2/getFileDownloadUrl.action?shortCode=%s&accessCode=%s&fileId=%s&subFileId=%s";


    public static String getListDirUrl(Cloud189ValidateParam param) {
        return getListDirUrl(param, "", -1, -1);
    }

    public static String getListDirUrl(Cloud189ValidateParam param, String fileId) {
        return getListDirUrl(param, fileId, -1, -1);
    }

    public static String getListDirUrl(Cloud189ValidateParam param, String fileId, int pageNum, int pageSize) {
        pageNum = pageNum > 0 ? pageNum : 1;
        pageSize = pageSize > 0 ? pageSize : 100;
        String url = String.format(LIST_DIR_URL, param.getShortCode(), param.getAccessCode(), param.getVerifyCode(), pageNum, pageSize);
        if (StringUtils.isNotEmpty(fileId)) {
            url = url + "&fileId=" + fileId;
        }

        return url;
    }

    public static String getGetFileDownloadUrl(Cloud189ValidateParam param, String fileId, String subFileId) {
        return String.format(GET_FILE_DOWNLOAD_URL, param.getShortCode(), param.getAccessCode(), fileId, subFileId);
    }

    public static String getFilePath(List<Path> pathList) {
        StringBuilder path = new StringBuilder();
        if (CollectionUtils.isNotEmpty(pathList)) {
            for (Path path1 : pathList) {
                path.append(path1.getFileName()).append(File.separator);
            }
        }
        return path.toString();
    }
}

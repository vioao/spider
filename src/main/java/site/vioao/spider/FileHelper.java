package site.vioao.spider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

@Slf4j
public class FileHelper {
    private static final String SEPARATOR = File.separator;
    private String path;
    private Pattern pattern;

    public FileHelper(String path, String nameRegex) {
        if (!path.endsWith(SEPARATOR)) {
            path += SEPARATOR;
        }

        this.path = path;
        this.pattern = Pattern.compile(nameRegex);
    }

    public boolean isValid(String relativePath, String name) {
        File file = new File(resolveFullFileName(relativePath, name));
        return !file.exists() && this.pattern.matcher(name).matches();
    }

    private String resolvePath(String path) {
        return this.path + path + File.separator;
    }

    private String resolveFullFileName(String path, String name) {
        return resolvePath(path) + File.separator + name;
    }

    public void persist(String relativePath, String name, byte[] bytes) {
        if (!isValid(relativePath, name)) {
            log.info("Ignore. file exists or filename not match. path: {}, name: {}", relativePath, name);
            return;
        }

        tryCreateFolder(relativePath);
        String filePath = resolveFullFileName(relativePath, name);
        try {
            FileUtils.writeByteArrayToFile(new File(filePath), bytes);
            log.info("persist file success. file: {}", filePath);
        } catch (IOException e) {
            log.error(String.format("persist file error. file: %s", filePath), e);
        }
    }

    private void tryCreateFolder(String path) {
        File file = new File(resolvePath(path));
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void main(String[] args) {
        boolean ma = Pattern.compile(".*\\.pdf").matcher("132132dddd.html.pdf").matches();
        System.out.printf("");
    }
}

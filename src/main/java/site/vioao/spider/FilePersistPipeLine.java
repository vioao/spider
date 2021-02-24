package site.vioao.spider;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


@Slf4j
public class FilePersistPipeLine implements Pipeline {
    private final FileHelper fileHelper;

    public FilePersistPipeLine(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        FileItem item = resultItems.get(Const.FILE_ITEM);
        if (item != null) {
            fileHelper.persist(item.getPath(), item.getFileName(), item.getBytes());
        }
    }
}

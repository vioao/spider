/**
  * Copyright 2021 bejson.com 
  */
package site.vioao.spider.cloud189.model;
import java.util.Date;

/**
 * Auto-generated: 2021-02-23 14:29:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private Date createTime;
    private String fileId;
    private String fileName;
    private long fileSize;
    private String fileType;
    private boolean isFolder;
    private Date lastOpTime;
    private String parentId;
    public void setCreateTime(Date createTime) {
         this.createTime = createTime;
     }
     public Date getCreateTime() {
         return createTime;
     }

    public void setFileId(String fileId) {
         this.fileId = fileId;
     }
     public String getFileId() {
         return fileId;
     }

    public void setFileName(String fileName) {
         this.fileName = fileName;
     }
     public String getFileName() {
         return fileName;
     }

    public void setFileSize(long fileSize) {
         this.fileSize = fileSize;
     }
     public long getFileSize() {
         return fileSize;
     }

    public void setFileType(String fileType) {
         this.fileType = fileType;
     }
     public String getFileType() {
         return fileType;
     }

    public void setIsFolder(boolean isFolder) {
         this.isFolder = isFolder;
     }
     public boolean getIsFolder() {
         return isFolder;
     }

    public void setLastOpTime(Date lastOpTime) {
         this.lastOpTime = lastOpTime;
     }
     public Date getLastOpTime() {
         return lastOpTime;
     }

    public void setParentId(String parentId) {
         this.parentId = parentId;
     }
     public String getParentId() {
         return parentId;
     }

}
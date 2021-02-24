/**
  * Copyright 2021 bejson.com 
  */
package site.vioao.spider.cloud189.model;
import java.util.List;
import java.util.Date;

/**
 * Auto-generated: 2021-02-23 14:29:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Cloud189FileItem {

    private AccessCount accessCount;
    private CreatorVO creatorVO;
    private List<Data> data;
    private String digest;
    private int expireTime;
    private int expireType;
    private int pageNum;
    private int pageSize;
    private List<Path> path;
    private int recordCount;
    private Date shareDate;
    public void setAccessCount(AccessCount accessCount) {
         this.accessCount = accessCount;
     }
     public AccessCount getAccessCount() {
         return accessCount;
     }

    public void setCreatorVO(CreatorVO creatorVO) {
         this.creatorVO = creatorVO;
     }
     public CreatorVO getCreatorVO() {
         return creatorVO;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setDigest(String digest) {
         this.digest = digest;
     }
     public String getDigest() {
         return digest;
     }

    public void setExpireTime(int expireTime) {
         this.expireTime = expireTime;
     }
     public int getExpireTime() {
         return expireTime;
     }

    public void setExpireType(int expireType) {
         this.expireType = expireType;
     }
     public int getExpireType() {
         return expireType;
     }

    public void setPageNum(int pageNum) {
         this.pageNum = pageNum;
     }
     public int getPageNum() {
         return pageNum;
     }

    public void setPageSize(int pageSize) {
         this.pageSize = pageSize;
     }
     public int getPageSize() {
         return pageSize;
     }

    public void setPath(List<Path> path) {
         this.path = path;
     }
     public List<Path> getPath() {
         return path;
     }

    public void setRecordCount(int recordCount) {
         this.recordCount = recordCount;
     }
     public int getRecordCount() {
         return recordCount;
     }

    public void setShareDate(Date shareDate) {
         this.shareDate = shareDate;
     }
     public Date getShareDate() {
         return shareDate;
     }

}
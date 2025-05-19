package com.xc.pojo;

 import com.github.houbb.opencc4j.util.ZhConverterUtil;

 import java.util.Date;
 public class SiteArticle {

     private Integer id;

     private String artTitle;

     private String artType;

     private String artImg;

     private String author;

     private Integer hitTimes;

     private Integer isShow;

     private Date addTime;

     private String artSummary;

     private String artCnt;

     private String spiderUrl;

     /*来源id*/
     private String sourceId;
     /*浏览量*/
     private Integer views;

     public SiteArticle(Integer id, String artTitle, String artType, String artImg, String author, Integer hitTimes, Integer isShow, Date addTime, String artSummary, String artCnt, String spiderUrl, String sourceId, Integer views) {

         this.id = id;

         this.artTitle = artTitle;

         this.artType = artType;

         this.artImg = artImg;

         this.author = author;

         this.hitTimes = hitTimes;

         this.isShow = isShow;

         this.addTime = addTime;

         this.artSummary = artSummary;

         this.artCnt = artCnt;

         this.spiderUrl = spiderUrl;
         this.sourceId = sourceId;
         this.views = views;

     }

     public SiteArticle() {
     }

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public String getArtTitle() {
         return artTitle;
     }

     public void setArtTitle(String artTitle) {
         this.artTitle = ZhConverterUtil.convertToTraditional(artTitle);
     }

     public String getArtType() {
         return artType;
     }

     public void setArtType(String artType) {
         this.artType = ZhConverterUtil.convertToTraditional(artType);
     }

     public String getArtImg() {
         return artImg;
     }

     public void setArtImg(String artImg) {
         this.artImg = artImg;
     }

     public String getAuthor() {
         return author;
     }

     public void setAuthor(String author) {
         this.author = ZhConverterUtil.convertToTraditional(author);
     }

     public Integer getHitTimes() {
         return hitTimes;
     }

     public void setHitTimes(Integer hitTimes) {
         this.hitTimes = hitTimes;
     }

     public Integer getIsShow() {
         return isShow;
     }

     public void setIsShow(Integer isShow) {
         this.isShow = isShow;
     }

     public Date getAddTime() {
         return addTime;
     }

     public void setAddTime(Date addTime) {
         this.addTime = addTime;
     }

     public String getArtSummary() {
         return artSummary;
     }

     public void setArtSummary(String artSummary) {
         this.artSummary = ZhConverterUtil.convertToTraditional(artSummary);;
     }

     public String getArtCnt() {
         return artCnt;
     }

     public void setArtCnt(String artCnt) {
         this.artCnt = ZhConverterUtil.convertToTraditional(artCnt);
     }

     public String getSpiderUrl() {
         return spiderUrl;
     }

     public void setSpiderUrl(String spiderUrl) {
         this.spiderUrl = spiderUrl;
     }

     public String getSourceId() {
         return sourceId;
     }

     public void setSourceId(String sourceId) {
         this.sourceId = sourceId;
     }

     public Integer getViews() {
         return views;
     }

     public void setViews(Integer views) {
         this.views = views;
     }
 }
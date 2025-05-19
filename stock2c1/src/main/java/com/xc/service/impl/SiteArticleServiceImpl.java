package com.xc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.SiteArticleMapper;
import com.xc.pojo.SiteArticle;
import com.xc.pojo.SiteNews;
import com.xc.service.ISiteArticleService;

import java.util.Date;
import java.util.List;

import com.xc.utils.DateTimeUtil;
import com.xc.utils.HttpRequest;
import com.xc.utils.PropertiesUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("iSiteArticleService")
public class SiteArticleServiceImpl
        implements ISiteArticleService {

    private static final Logger log = LoggerFactory.getLogger(SiteArticleServiceImpl.class);

    @Autowired
    SiteArticleMapper siteArticleMapper;

    public ServerResponse<PageInfo> listByAdmin(String artTitle, String artType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SiteArticle> siteArticles = this.siteArticleMapper.listByAdmin(artTitle, artType);
        PageInfo pageInfo = new PageInfo(siteArticles);
        return ServerResponse.createBySuccess(pageInfo);
    }


    public ServerResponse add(SiteArticle siteArticle) {
        if (StringUtils.isBlank(siteArticle.getArtTitle()) ||
                StringUtils.isBlank(siteArticle.getArtType()) ||
                StringUtils.isBlank(siteArticle.getArtCnt()) || siteArticle
                .getIsShow() == null) {
            return ServerResponse.createByErrorMsg("标题正文类型必填");
        }

        siteArticle.setAddTime(new Date());

        int insertCount = this.siteArticleMapper.insert(siteArticle);
        if (insertCount > 0) {
            return ServerResponse.createBySuccessMsg("添加成功");
        }
        return ServerResponse.createByErrorMsg("添加失败");
    }


    public ServerResponse update(SiteArticle siteArticle) {
        if (siteArticle.getId() == null) {
            return ServerResponse.createByErrorMsg("修改id必传");
        }
        int updateCount = this.siteArticleMapper.updateByPrimaryKeySelective(siteArticle);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("修改成功");
        }
        return ServerResponse.createByErrorMsg("修改失败");
    }

    public ServerResponse del(Integer artId) {
        if (artId == null) {
            return ServerResponse.createByErrorMsg("删除id必传");
        }
        int updateCount = this.siteArticleMapper.deleteByPrimaryKey(artId);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }


    public ServerResponse detail(Integer artId) {
        return ServerResponse.createBySuccess(this.siteArticleMapper.selectByPrimaryKey(artId));
    }


    public ServerResponse list(String artTitle, String artType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SiteArticle> siteArticles = this.siteArticleMapper.list(artTitle, artType);
        PageInfo pageInfo = new PageInfo(siteArticles);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /*top最新公告*/
    @Override
    public ServerResponse getTopArtList(int pageSize){
        List<SiteNews> listData = this.siteArticleMapper.getTopArtList(pageSize);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(listData);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /*公告-抓取*/
    @Override
    public int grabArticle() {
        int ret = 0;
        try {
            Date date = DateTimeUtil.addDay(new Date(),2);
            String time = DateTimeUtil.dateToStr(date,"yyyy-MM-dd HH:mm:ss");
            String smap = DateTimeUtil.dateToStamp(time);
            String url = PropertiesUtil.getProperty("news.main.url") + "/pc_news/Notice/GetNoticeList?uid=&columnType=002&securityType=100&pageNumber=1&pageSize=50&startTime=1589904000000&endTime="+ smap +"&securityCodeMarket=&searchType=001&searchCondition=&fundIdList=&isfund=";
            String newlist = HttpRequest.doGrabGet(url);
            JSONObject data = JSONObject.fromObject(newlist);
            JSONObject json = JSONObject.fromObject(data.get("data"));
            if(json != null && json.getJSONArray("items") != null && json.getJSONArray("items").size() > 0){
                for (int i = 0; i < json.getJSONArray("items").size(); i++){
                    JSONObject model = JSONObject.fromObject(json.getJSONArray("items").getString(i));
                    String newsId = model.getString("infoCode");

                    //新闻不存在则添加
                    if(siteArticleMapper.getArtBySourceIdCount(newsId) == 0){
                        //获取新闻详情
                        String newdata = HttpRequest.doGrabGet("https://np-cnotice-pc.eastmoney.com/api/content/ann/rich?client_source=pc&page_index=1&is_rich=1&req_trace=&art_code="+ newsId);

                        JSONObject jsonnew = JSONObject.fromObject(newdata);
                        if(jsonnew != null && jsonnew.get("data") != null){
                            JSONObject news = JSONObject.fromObject(jsonnew.get("data"));
                            SiteArticle siteArticle = new SiteArticle();
                            siteArticle.setSourceId(newsId);
                            siteArticle.setAuthor(news.getString("short_name"));
                            siteArticle.setArtTitle(news.getString("notice_title"));
                            siteArticle.setAddTime(DateTimeUtil.getCurrentDate());
                            siteArticle.setArtSummary(news.getString("notice_title"));
                            String content = news.getString("notice_content");
                            siteArticle.setIsShow(0);
                            siteArticle.setViews(10);
                            siteArticle.setArtType(news.getString("short_name"));
                            if(!content.contains("{") && !content.contains("}")){
                                siteArticle.setArtCnt(content);
                                siteArticleMapper.insert(siteArticle);
                                ret++;
                            } else if (content.contains("{") && content.contains("pages")){
                                JSONObject pagelist = JSONObject.fromObject(news.get("notice_content"));
                                String contStr = "";
                                if(pagelist != null && pagelist.getJSONArray("pages") != null && pagelist.getJSONArray("pages").size() > 0){
                                    for (int k=0;k<pagelist.getJSONArray("pages").size();k++){
                                        JSONObject pageslist = JSONObject.fromObject(pagelist.getJSONArray("pages").getString(k));
                                        if(pageslist != null && pageslist.getJSONArray("pageContent") != null && pageslist.getJSONArray("pageContent").size() > 0){
                                            for (int r=0;r<pageslist.getJSONArray("pageContent").size();r++){
                                                JSONObject cont = JSONObject.fromObject(pageslist.getJSONArray("pageContent").getString(r));
                                                if(!cont.toString().contains("kids")){
                                                    contStr = contStr + cont.get("content");
                                                } else {
                                                    JSONObject contkids = JSONObject.fromObject(cont.getJSONArray("kids").get(0));
                                                    contStr = contStr + contkids.get("content");
                                                }
                                            }
                                        }
                                    }
                                    if(StringUtils.isNotEmpty(contStr)){
                                        siteArticle.setArtCnt(contStr);
                                        siteArticleMapper.insert(siteArticle);
                                        ret++;
                                    }
                                }

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("公告抓取条数：" + ret);

        return ret;
    }

}

package com.xc.utils.task.news;

import com.xc.service.ISiteArticleService;
import com.xc.service.ISiteNewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsTask {
    private static final Logger log = LoggerFactory.getLogger(NewsTask.class);

    @Autowired
    ISiteNewsService iSiteNewsService;

    @Autowired
    ISiteArticleService iSiteArticleService;

    /*
    * 新闻资讯抓取
    * */
    @Scheduled(cron = "0 0 9-18 * * ?")
    public void NewsInfoTask() {
        this.iSiteNewsService.grabNews();
    }

    /*
     * 新闻资讯抓取
     * */
    @Scheduled(cron = "0 0 9-18 * * ?")
    public void ArtInfoTask() {
        this.iSiteArticleService.grabArticle();
    }
}

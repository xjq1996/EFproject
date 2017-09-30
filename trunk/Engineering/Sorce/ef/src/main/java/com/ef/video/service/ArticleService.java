package com.ef.video.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.video.dao.ArticleDao;
import com.ef.video.dto.ArticleQueryDto;
import com.ef.video.dto.CurrentArticleInfoDTO;
import com.ef.video.entity.Article;
import com.ef.video.entity.Page;

@Service
public class ArticleService extends CommonService< Article,String >  {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    public void setArticleDao(ArticleDao articleDao){
      super.setCommonDao(articleDao);
    }

    public Page<Article> queryArticlePage(ArticleQueryDto articleQueryDTO){
           return this.articleDao.queryArticlePage(articleQueryDTO);
    }

    public List<Article> queryArticleList(ArticleQueryDto articleQueryDTO){
           return this.articleDao.queryArticleList(articleQueryDTO);
    }
    
    public List<Map<String, Object>> queryStatisMapList(ArticleQueryDto articleQueryDTO){
    	   return this.articleDao.queryStatisMapList(articleQueryDTO);
    }
    
    public Article queryNextArticle(CurrentArticleInfoDTO currentArticleInfoDTO){
    	   Article article = null;
    	   List<Article> articleList = this.articleDao.queryNextArticleList(currentArticleInfoDTO);
    	   if(articleList != null && articleList.size() > 0){
    		   article = articleList.get(0);
    	   }
    	   return article;
    }
    
    public Article queryPreArticle(CurrentArticleInfoDTO currentArticleInfoDTO){
           Article article = null;
           List<Article> articleList = this.articleDao.queryPreArticleList(currentArticleInfoDTO);
           if(articleList != null && articleList.size() > 0){
    		   article = articleList.get(0);
    	   }
    	   return article;
    }


}

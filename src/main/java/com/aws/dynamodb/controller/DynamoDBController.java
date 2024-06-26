package com.aws.dynamodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aws.dynamodb.entity.Article;
import com.aws.dynamodb.service.ArticleDao;

@RestController
public class DynamoDBController {
	
	@Autowired
	private ArticleDao articleDao;
	
	@PostMapping("/saveArticle") 
    public String saveToDynamoDBArticleTable(@RequestBody Article article) 
    { 
		Article articleToSave = Article.builder()
				.id(article.getId())
				.title(article.getTitle())
				.content(article.getContent())
				.build();

		articleDao.save(articleToSave);
		return "Article with id "+ article.getId()+ " , is saved successfully.";
    } 
	
	@GetMapping("/allArticle") 
    public List<Article> fetchRecordsFromDynamoDBArticleTable() 
    { 
		return articleDao.scan();
    }
	
	@GetMapping("/article/{id}") 
    public Article fetchRecordsFromDynamoDBArticleTable(@PathVariable("id") String id ) 
    { 
		Article article = articleDao.getItem(id);
		if(null!=article) {
			return article;
		}else {
			//Here no article exist message is added in title if Article class itself, 
			//but in practice there should be separate response object designed for this.
			return new Article(id.toString(),"No Article exist with id " +id,"");
		}
		
    }
	
	@PutMapping("/updateArticle")
    public String updateRecordInDynamoDBArticleTable(@RequestBody Article article)
    { 
		Article updateArticle = Article.builder()
				.id(article.getId())
				.title(article.getTitle())
				.content(article.getContent())
				.build();
		articleDao.update(updateArticle);
		return "Article with id "+ article.getId()+ " , is updated successfully.";
    }
	
	@DeleteMapping("/article/{id}") 
    public String deleteRecordsFromDynamoDBArticleTable(@PathVariable("id") String id ) 
    { 
		Article article = articleDao.getItem(id);
		articleDao.delete(article);
		return "Article with id "+ article.getId()+ " , is deleted successfully.";
    }
}

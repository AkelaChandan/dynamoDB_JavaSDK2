package com.aws.dynamodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.dynamodb.entity.Article;
import com.aws.dynamodb.service.ArticleDao;

@RestController
public class DynamoDBController {
	
	@Autowired
	private ArticleDao articleDao;
	
	@PostMapping("/saveArticle") 
    public void saveToDynamoDBArticleTable() 
    { 
		Article article1 = Article.builder()
				.id("2")
				.title("t1")
				.content("c1")
				.build();
		Article article2 = Article.builder()
				.id("3")
				.title("t2")
				.content("c2")
				.build();
		articleDao.save(article1);
		articleDao.save(article2);
    } 
	
	@GetMapping("/allArticle") 
    public void fetchRecordsFromDynamoDBArticleTable() 
    { 
		for (Article article : articleDao.scan()) {
			System.out.println("article = " + article);
		}
    }
	
	@GetMapping("/article/{id}") 
    public Article fetchRecordsFromDynamoDBArticleTable(@PathVariable("id") String id ) 
    { 
		Article a = articleDao.getItem(id);
		System.out.println("a = " + a);
		return a;
    }
	
	@PutMapping("/updateArticle")///{id}") 
    public void updateRecordInDynamoDBArticleTable()//@PathVariable("id") String id ) 
    { 
		System.out.println("inside controller");
		Article article1 = Article.builder()
				.id("2")
				.title("t1_updated")
				.content("c1_updated")
				.build();
		articleDao.update(article1);
		//System.out.println("a = " + a);
		//return a;
    }
}

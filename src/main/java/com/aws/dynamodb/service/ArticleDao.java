package com.aws.dynamodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.dynamodb.entity.Article;

import lombok.AllArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Service
@AllArgsConstructor
public class ArticleDao {
	

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public Article getItem(String id) {
        return getMappedTable(Article.class).getItem(Key.builder().partitionValue(id).build());
    }

    public void save(Article article) {
    	System.out.println(article);
        getMappedTable(Article.class).putItem(article);

    }

    public List<Article> scan() {
        return getMappedTable(Article.class).scan().items().stream().toList();
    }

    private <T> DynamoDbTable<T> getMappedTable(Class<T> type) {
        return dynamoDbEnhancedClient.table("Article", TableSchema.fromBean(type));
    }
    
    public void update(Article article) {
    	System.out.println("article = "+ article);
        getMappedTable(Article.class).putItem(article);

    }

}
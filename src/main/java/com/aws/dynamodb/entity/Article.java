package com.aws.dynamodb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class Article {
    String id;

 
    @DynamoDbPartitionKey //primary key
    @DynamoDbAttribute(value = "id") //column name
    public String getId() {
        return id;
    }

 //   @DynamoDbAttribute(value = "title") //column name
    String title;
    
//    @DynamoDbAttribute(value = "content") //column name
    String content;

}

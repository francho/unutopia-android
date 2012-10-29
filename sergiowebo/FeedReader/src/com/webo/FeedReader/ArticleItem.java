package com.webo.FeedReader;

public class ArticleItem {
    
	private String title;
	private String author;
	private String date;

    
    public ArticleItem(){
        super();
    }
    
    public String getTitle(){
    	return title;
    }
    
    public String getAuthor(){
    	return author;
    }
    
    public String getDate(){
    	return date;
    }
    
    public void setTitle(String title){
    	this.title = title;
    }
    
    public void setAuthor(String author){
    	this.author = author;
    }
    
    public void setDate(String date){
    	this.date = date;
    }
    
    public ArticleItem(String title, String author, String date) {
        super();
        this.title = title;
        this.author = author;
        this.date = date;
    }
}
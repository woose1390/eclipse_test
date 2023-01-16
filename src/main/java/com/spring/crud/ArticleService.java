package com.spring.crud;

public interface ArticleService {

	ArticleVO selectById(ArticleVO vo);

	void insert(ArticleVO vo);

	void update(ArticleVO vo);
	
	void delete(ArticleVO vo);
}

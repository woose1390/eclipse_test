package com.spring.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crud.ArticleService;
import com.spring.crud.ArticleVO;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDAO articleDAO;
	
	
	@Override
	public ArticleVO selectById(ArticleVO vo) {
		return articleDAO.selectById(vo);
	}

	@Override
	public void insert(ArticleVO vo) {
		articleDAO.insert(vo);
	}

	@Override
	public void update(ArticleVO vo) {
		articleDAO.update(vo);
	}

	@Override
	public void delete(ArticleVO vo) {
		articleDAO.delete(vo);
	}	
}




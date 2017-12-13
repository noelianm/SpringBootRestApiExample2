package com.websystique.springboot.service;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.websystique.springboot.model.Answer;
import com.websystique.springboot.model.Comment;

public interface ProcesorService {
	
	
	Comment saveAnswer(String a);
	
	void updateAnswer(Answer b);
	
	List<Answer> findAllAnswer();
	
	void deleteAllAnswer();

	String getListagramas(String mensaje);
	
	Answer findById(long id);
	
	
}

package com.websystique.springboot.service;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springboot.model.Comment;


public interface ProcesorServiceNgramsRequest {
	
	
	void saveNgrams(Comment a);
	
	int getUnigramComment(String busqueda);
	
	int getBigramComment(String busqueda);
	
	int getTrigramComment(String busqueda);
	
	List getAllUnigrams();

    List getAllBigrams();

	List getAllTrigrams();
	
	
}

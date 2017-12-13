package com.websystique.springboot.service;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.lang.System;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.websystique.springboot.model.Answer;
import com.websystique.springboot.model.Comment;

@Service("ProcesorServiceNgrams")
public class ProcesorServiceNgramsRequestImpl implements ProcesorServiceNgramsRequest{
	
	private static final AtomicLong counter = new AtomicLong();
	
	
	private static List listUnigrams = new ArrayList<String>();

	private static ArrayList<String> listBigrams = new ArrayList<String>();
	private static ArrayList<String> listTrigrams = new ArrayList<String>();


	public List getAllUnigrams() {
		return this.listUnigrams;
	}


	public List getAllBigrams() {
		return this.listBigrams;
	}

	
	public List getAllTrigrams() {
		return this.listTrigrams;
	}
	
	

	/*public void saveAnswer(Answer a) {
		a.setId(counter.incrementAndGet());
		listAnswer.add(a);
	}
*/


	public void  saveNgrams(Comment mensaje) {

	    ArrayList<String> unigrams = mensaje.getUniqueUnigrams();
		ArrayList<String> bigrams = mensaje.getListBigrams();
		ArrayList<String> trigrams = mensaje.getListTrigrams();
		
		listUnigrams.addAll(unigrams);
		listBigrams.addAll(bigrams);
		listTrigrams.addAll(trigrams);
    
       
   }

   public int getUnigramComment(String busqueda){
	  int vsize = listUnigrams.size();
		int result = 0;
		boolean searching = true;
		int i = 0;
	
		while (i < vsize) {

			//listUnigrams.add(new Ngrams(unigrams.get(i)))
			if(listUnigrams.get(i).equals(busqueda) ) {
				result++;
				
			}
			i++;  
		}
     return result;

   }


   
   public int getBigramComment(String busqueda){
	  int vsize = listBigrams.size();
		int result = 0;
		boolean searching = true;
		int i = 0;
	
		while (i < vsize) {

			//listUnigrams.add(new Ngrams(unigrams.get(i)))
			if(listBigrams.get(i).equals(busqueda) ) {
				result++;
				
			}
			i++;  
		}
     return result;

   }


   public int getTrigramComment(String busqueda){
	  int vsize = listTrigrams.size();
		int result = 0;
		boolean searching = true;
		int i = 0;
	
		while (i < vsize) {

			//listUnigrams.add(new Ngrams(unigrams.get(i)))
			if(listTrigrams.get(i).equals(busqueda) ) {
				result++;
				
			}
			i++;  
		}
     return result;

   }




	

    

}
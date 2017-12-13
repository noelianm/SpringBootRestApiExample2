package com.websystique.springboot.service;

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

@Service("ProcesorService")
public class ProcesorServiceImpl implements ProcesorService{
	
		
	private static List<Answer> listAnswer = new ArrayList<Answer>();;
    private static final AtomicLong counter = new AtomicLong();

	

	public List<Answer> findAllAnswer() {
		return listAnswer;

	}
	

	public Comment  saveAnswer(String mensaje) {
	   //SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
       //String current_time_str = time_formatter.format(System.currentTimeMillis());
	   long time = System.currentTimeMillis();
		
      // System.out.println("Inicio de peticin: " + current_time_str);
	   //String resp = getListagramas(a);
	   
       String commentProcesar = "_I "+ mensaje + " _F";
       System.out.println("Mensaje recibido por server "+ commentProcesar); 
       Comment comentario = new Comment(commentProcesar);
       System.out.println("Server procesa solicitud");
       String temp1 = comentario.getPrintUnigrams();
	   String temp2 = comentario.getPrintBigrams();
       String temp3 = comentario.getPrintTrigrams();
	   long time2 = System.currentTimeMillis() - time;
       System.out.println("Dif:" + time2);

	   // String current_time_str2 = time_formatter.format(System.currentTimeMillis());
		
       //System.out.println("Fin de peticion de ngramas": + current_time_str2);

	   listAnswer.add(new Answer(counter.incrementAndGet(), mensaje,temp1, temp2, temp3, time2));
	   return comentario;
	}

	public void updateAnswer(Answer b) {
		int index = listAnswer.indexOf(b);
		listAnswer.set(index, b);
	}

	public void deleteAllAnswer(){
		listAnswer.clear();
	}


	public Answer findById(long id) {
		for(Answer answ : listAnswer){
			if(answ.getId() == id){
				return answ;
			}
		}
		return null;
	}
	

	/**
     * Metodo remoto que retorna la lista de ngramas generados por el objeto Comment hacia el cliente.
     */
  
    public  String getListagramas(String mensaje) {
       String commentProcesar = "_I "+ mensaje + " _F";
       System.out.println("Mensaje recibido por server "+ commentProcesar); 
       Comment comentario = new Comment(commentProcesar);
       System.out.println("Server procesa solicitud");
       String temp = comentario.getPrintAllGramas();
       return temp;
    }


	/*private static List<Answer> populateDummy(){
		List<Answer> listAnswer = new ArrayList<Answer>();
		listAnswer.add(new Answer(counter.incrementAndGet(),"texto de prueba es esto","test", "test","test", 0));
		listAnswer.add(new Answer(counter.incrementAndGet(),"texto de prueba es esto","test", "test","test", 0));
		listAnswer.add(new Answer(counter.incrementAndGet(),"texto de prueba es esto","test", "test","test",0));
		listAnswer.add(new Answer(counter.incrementAndGet(),"texto de prueba es esto","test", "test","test",0));
		return listAnswer;
	}*/

    

}
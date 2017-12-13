package com.websystique.springboot.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.System;


/**
 * Clase de Comments que permite analizar cada comentario 
 */

/**
 * @author nnavarro 
 *
 */
public class Comment {
    private ArrayList<String> unigrams = new ArrayList<String>();
    private ArrayList<String> listBigrams =  new ArrayList<String>();
    private ArrayList<String> listTrigrams =  new ArrayList<String>();
    private ArrayList<String[]> bigrams = new ArrayList<String[]>();
    private ArrayList<String[]> trigrams = new ArrayList<String[]>();
    private ArrayList<String> uniqueUnigrams = new ArrayList<String>();
    private String commentText;
    
public  Comment(String input)   {
    
    this.commentText =  input;
     validateUnigrams();
     // printUnigrams();
     createUnigrams();
     createBigrams();
    // printBigrams();
     createTrigrams();
    // printTrigrams();
    
    
}
/**
 * Metodo que valida si los unigramas son unicos. Returns una lista sin unigramas repetidos.
 */
private void validateUnigrams() {
         String words = removeDuplicatedWord(commentText);
    String[] unigramsToBe = words.split("\\s"); //cambio estaba con W+ - revisar
    for (int i=0; i < unigramsToBe.length; i++) {
        this.uniqueUnigrams.add(unigramsToBe[i]);
        

    }
}
/**
 * Metodo que crea una lista con  unigramas,que pueden estar repetidos.
 */
private void  createUnigrams() {

    String[] unigramsToBe = commentText.split("\\s"); //cambio estaba con W+ - revisar
    for (int i=0; i < unigramsToBe.length; i++) {
        this.unigrams.add(unigramsToBe[i]);
        

    }
}

/**
 * Metodo que remueve duplicados de un string. Devuelve el string sin duplicados.
 */
private String removeDuplicatedWord(String s) {
    return Arrays.stream(s.split(" ")).distinct().collect(Collectors.joining(" "));
    
}


/**
 * Metodo que crea una lista de bigramas
 * 
 */
private void createBigrams() {
    int vsize = this.unigrams.size();
    //System.out.println("size--------------"+vsize);
    for (int i = 0;i+1 < vsize; i++) {
    String[] vbigram  = {this.unigrams.get(i),this.unigrams.get(i+1)};
    if(!getBigramsCount(this.unigrams.get(i),this.unigrams.get(i+1))) {
            bigrams.add(vbigram);
           this.listBigrams.add(this.unigrams.get(i) + "||" + this.unigrams.get(i+1));

     }
    }
}

/**
 * Metodo que remueve duplicados de un string. Devuelve el string sin duplicados.
 */
private String removeDuplicatedBigram(String s) {
    
    return Arrays.stream(s.split(" ")).distinct().collect(Collectors.joining(" "));
    
}

/**
 * Metodo que crea una lista de trigramas
 */
private void createTrigrams() {
    int vsize = this.unigrams.size();
    for (int i = 0; i+2 < vsize; i++) {
            String[] vtrigram  = {this.unigrams.get(i),this.unigrams.get(i+1),this.unigrams.get(i+2)};
          if(!getTrigramsCount(this.unigrams.get(i),this.unigrams.get(i+1),this.unigrams.get(i+2))) {
              trigrams.add(vtrigram); 
               this.listTrigrams.add(this.unigrams.get(i) + "||" + this.unigrams.get(i+1) + "||" + this.unigrams.get(i+2));

            }
        
        }
}

/**
 * Metodo que revisa si una palabra esta en los unigramas 
 */

public boolean searchUnigram(String unigramToFind) {
    if(this.unigrams.contains(unigramToFind)) {
        return true;
    }
    else
    {
        return false;
    }   
}

/**
 * Metodo que escribe en consola lista  unigramas  de un comentario
 */
public void printUnigrams() {
    System.out.println("UNIGRAMS");
    for (String temp : unigrams) {
        System.out.println(temp);
    }
}

/**
 * Metodo que escribe en consola lista  bigramas  de un comentario
 */
public void printBigrams() {
    int vsize = bigrams.size();
    System.out.println("BIGRAMS " + vsize);
    for (int i = 0;i < vsize; i++) {
        System.out.println(bigrams.get(i)[0] + ","+bigrams.get(i)[1]);
    
    }
}

/**
 * Metodo que escribe en consola lista  triigramas  de un comentario
 */
public void printTrigrams() {
    int vsize = trigrams.size();
    System.out.println("TRIGRAMS " + vsize);
    for (int i = 0;i < vsize; i++) {
        System.out.println(trigrams.get(i)[0] + ","+trigrams.get(i)[1]+ ","+trigrams.get(i)[2]);
    
    }
}

/**
 * Metodo que obtiene todos los unigramas asociados al comentario
 */
public ArrayList<String> getUnigrams() {
    // TODO Auto-generated method stub
    return this.unigrams;
}

/**
 * Metodo que obtiene los unigramas unicos asociados al comentario
 */
public ArrayList<String> getUniqueUnigrams() {
    // TODO Auto-generated method stub
    return this.uniqueUnigrams;
}

/**
 * Metodo que obtiene los bigramas asociados al comentario
 */
public ArrayList<String[]> getBigrams() {
    // TODO Auto-generated method stub
    return this.bigrams;
}


public ArrayList<String> getListBigrams() {
    // TODO Auto-generated method stub
    return this.listBigrams;
}
/**
 * Metodo que busca un unigrama en los unigramas asociados al comentario
 */
public boolean getUnigramsCount(String test) {
    // TODO Auto-generated method stub
    int vsize = unigrams.size();
    boolean result = false;
    boolean searching = true;
    int i = 0;
    while (i < vsize && searching == true) {
        if(unigrams.get(i).equals(test) ) {
            result= true;
            searching = false;
        }
        i++;  
    }
    return result;
}


/**
 * Metodo que busca un bigrama en los bigramas asociados al comentario
 */
public boolean getBigramsCount(String test, String test2) {
    // TODO Auto-generated method stub
    int vsize = bigrams.size();
    boolean result = false;
    boolean searching = true;
    int i = 0;
    while (i < vsize && searching == true) {
        if(bigrams.get(i)[0].equals(test)  &&  bigrams.get(i)[1].equals(test2)) {
            result= true;
            searching = false;
        }
        i++;  
    }
    return result;
}


/**
 * Metodo que busca un trigrama en los trigramas asociados al comentario
 */
public boolean getTrigramsCount(String test, String test2,String test3) {
    // TODO Auto-generated method stub
    int vsize = trigrams.size();
    boolean result = false;
    boolean searching = true;
    int i = 0;
    while (i < vsize && searching == true) {
        if(trigrams.get(i)[0].equals(test)  &&  trigrams.get(i)[1].equals(test2) && trigrams.get(i)[2].equals(test3)) {
            result= true;
            searching = false;
        }
        i++;  
    }
    return result;
}


/**
 * Metodo que retorna   los trigramas asociados al comentario
 */
public ArrayList<String[]> getTrigrams() {
    // TODO Auto-generated method stub
    return this.trigrams;
}

public ArrayList<String> getListTrigrams() {
    // TODO Auto-generated method stub
    return this.listTrigrams;
}

/**
 * Metodo que retorna un string con los unigramas, bigramas y trigramas del comentario
 */
public String getPrintAllGramas() {
    String respuesta ="";
//Imprime los unigramas en el cliente
           for (String temp : uniqueUnigrams) {
		respuesta = respuesta + " "+ "["+ temp +"]";
		}

           //Imprime los bigramas en el cliente
           int vsize = bigrams.size();
	   for (int i = 0;i < vsize; i++) {
		respuesta =  respuesta + " "+ "["+ bigrams.get(i)[0] + ","+bigrams.get(i)[1] +"]";
	   }
	   
	   //Imprime los trigramas en el cliente
	    vsize = trigrams.size();
	    for (int i = 0;i < vsize; i++) {
		respuesta = respuesta +  " "+"["+ trigrams.get(i)[0] + ","+trigrams.get(i)[1]+ ","+trigrams.get(i)[2]+" ]";
	   }
	   return respuesta;
  }


public String getPrintUnigrams(){
	 String respuesta ="";
//Imprime los unigramas en el cliente
     for (String temp : uniqueUnigrams) {
		respuesta = respuesta + "["+ temp +"]";
		}
	return respuesta;

}

public String getPrintBigrams(){
	 String respuesta ="";

    //Imprime los bigramas en el cliente
           int vsize = bigrams.size();
	   for (int i = 0;i < vsize; i++) {
		respuesta =  respuesta +  "["+ bigrams.get(i)[0] + ","+bigrams.get(i)[1] +"]";
	   }
	return respuesta;

}
public String getPrintTrigrams(){
	 String respuesta ="";
    int vsize = trigrams.size();
  //Imprime los trigramas en el cliente
	    for (int i = 0;i < vsize; i++) {
		respuesta = respuesta +"["+ trigrams.get(i)[0] + ","+trigrams.get(i)[1]+ ","+trigrams.get(i)[2]+"]";
	   }
	return respuesta;

}


}


package com.websystique.springboot.model;
	
public class Answer {
	private long id;

	private String comment;
	
	private String unigrams;
	
	private String bigrams;
	
	private String trigrams;

	private long processTimeNgrams;

	//private String inicioPeticion;
	
	//private String finPeticion;



	public Answer(){
		this.id=0;
		this.processTimeNgrams = 0;
	}
	
	public Answer(long a, String comment, String unigrams, String bigrams, String trigrams, long timeRequest){
		this.id = a;
		this.comment = comment;
		this.unigrams = unigrams;
		this.trigrams = trigrams;
		this.bigrams = bigrams;		
		this.processTimeNgrams = timeRequest;
		
		
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long a) {
		this.id = a;
	}

	public long getProcessTimeNgrams() {
		return this.processTimeNgrams;
	}

	public void setProcessTimeNgrams(long a) {
		this.processTimeNgrams = a;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUnigrams() {
		return unigrams;
	}

	public void setUnigrams(String unigrams) {
		this.unigrams = unigrams;
	}

	public String getBigrams() {
		return bigrams;
	}

	public void setBigrams(String bigrams) {
		this.bigrams = bigrams;
	}

	public String getTrigrams() {
		return trigrams;
	}

	public void setTrigrams(String trigrams) {
		this.trigrams = trigrams;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.id ^ (this.id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (this.id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [text=" + comment + ", unigrams=" + unigrams + ", bigrams=" + bigrams
				+ ", trigrams=" + trigrams + ",Inicio de procesamiento =" + processTimeNgrams +"]";
	}


}

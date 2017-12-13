package com.websystique.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;
import com.websystique.springboot.model.Answer;

import com.websystique.springboot.model.Comment;
import com.websystique.springboot.service.ProcesorServiceNgramsRequest;
import com.websystique.springboot.service.ProcesorService;
import com.websystique.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/analyze")
public class ProcessorApiController {

	public static final Logger logger = LoggerFactory.getLogger(ProcessorApiController.class);
	private static final AtomicLong counter = new AtomicLong();

	@Autowired
	ProcesorService useService; //Service which will do all data retrieval/manipulation work

	@Autowired
	ProcesorServiceNgramsRequest useServiceCount; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Answer---------------------------------------------

	@RequestMapping(value = "/answer/", method = RequestMethod.GET)
	public ResponseEntity<List<Answer>> listAllAnswer() {
		List<Answer> lista = useService.findAllAnswer();
		if (lista.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Answer>>(lista, HttpStatus.OK);
	}

	// -------------------Create a ngrams-------------------------------------------

	@RequestMapping(value = "/answer/", method = RequestMethod.POST)
	public ResponseEntity<?> createAnswer(@RequestBody Answer answer, UriComponentsBuilder ucBuilder) {
		logger.info("Creating request : {}", answer);
		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        String current_time_str = time_formatter.format(System.currentTimeMillis());
        
		//long idplus =  counter.incrementAndGet();
	    Comment comment = useService.saveAnswer(answer.getComment());
		answer.setId(counter.incrementAndGet());

		useServiceCount.saveNgrams(comment);
		//int response = useServiceCount.getUnigramComment("es");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/analyze/answer/{id}").buildAndExpand(answer.getId()).toUri());
		 String current_time_str2 = time_formatter.format(System.currentTimeMillis());
		return new ResponseEntity<String>( headers + " Comment:" + answer.getComment()  +" Inicio: " + current_time_str + " Fin: "+ current_time_str2, HttpStatus.CREATED);
	}


	// -------------------Create a request Unigram-------------------------------------------

	@RequestMapping(value = "/unigram/", method = RequestMethod.POST)
	public ResponseEntity<?> createAnswerUnigram(@RequestBody String requestNgramCount) {
		logger.info("Creating request : {}", requestNgramCount);

		int response = useServiceCount.getUnigramComment(requestNgramCount);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/analyze/unigran/{id}").buildAndExpand(answer.getId()).toUri());
		return new ResponseEntity<String>(  "UNIGRAM =" + requestNgramCount + " Quantity =" + response  , HttpStatus.CREATED);
	}

	// -------------------Create a request bigram-------------------------------------------

	@RequestMapping(value = "/bigram/", method = RequestMethod.POST)
	public ResponseEntity<?> createAnswerBigram(@RequestBody String requestNgramCount) {
		logger.info("Creating request : {}", requestNgramCount);

		int response = useServiceCount.getBigramComment(requestNgramCount);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/analyze/unigran/{id}").buildAndExpand(answer.getId()).toUri());
		return new ResponseEntity<String>(  "BIGRAM =" + requestNgramCount + " Quantity =" + response  , HttpStatus.CREATED);
	}

	// -------------------Create a request trigram-------------------------------------------

	@RequestMapping(value = "/trigram/", method = RequestMethod.POST)
	public ResponseEntity<?> createAnswerTrigram(@RequestBody String requestNgramCount) {
		logger.info("Creating request : {}", requestNgramCount);

		int response = useServiceCount.getTrigramComment(requestNgramCount);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/analyze/unigran/{id}").buildAndExpand(answer.getId()).toUri());
		return new ResponseEntity<String>(  "TRIGRAM =" + requestNgramCount + " Quantity =" + response  , HttpStatus.CREATED);
	}

	// -------------------Retrieve All Unigrams---------------------------------------------

	@RequestMapping(value = "/unigram/all", method = RequestMethod.GET)
	public ResponseEntity<List<String>> listAllUnigram() {
		List<String> lista = useServiceCount.getAllUnigrams();
		if (lista.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}

	// -------------------Retrieve All Bigrams---------------------------------------------

	@RequestMapping(value = "/bigram/all", method = RequestMethod.GET)
	public ResponseEntity<List<String>> listAllBigrams() {
		List<String> lista = useServiceCount.getAllBigrams();
		if (lista.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}

		// -------------------Retrieve All Trigrams---------------------------------------------

	@RequestMapping(value = "/trigram/all", method = RequestMethod.GET)
	public ResponseEntity<List<String>> listAllTrigrams() {
		List<String> lista = useServiceCount.getAllTrigrams();
		if (lista.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}


	
	// -------------------Retrieve Single Answer------------------------------------------

	@RequestMapping(value = "/answer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Answer> getAnswerOne(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		Answer answ = useService.findById(id);
		if (answ == null) {
			logger.error("Answer with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Answer with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Answer>(answ, HttpStatus.OK);
	}

	// ------------------- Delete All Answers-----------------------------

	@RequestMapping(value = "/answer/", method = RequestMethod.DELETE)
	public ResponseEntity<Answer> deleteAllAnswer() {
		logger.info("Deleting All Users");

		useService.deleteAllAnswer();
		return new ResponseEntity<Answer>(HttpStatus.NO_CONTENT);
	}

}
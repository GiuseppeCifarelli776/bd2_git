package com.DiVaioCifarelli.progetto_BD2.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Arrays;

import org.bson.BsonString;
import org.bson.Document;

import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.unwind;
import static com.mongodb.client.model.Accumulators.avg;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.CommandResult;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.concurrent.TimeUnit;
import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.DbCallback;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DiVaioCifarelli.progetto_BD2.MongoConfig;
import com.DiVaioCifarelli.progetto_BD2.model.Credits;
import com.DiVaioCifarelli.progetto_BD2.model.Film;
import com.DiVaioCifarelli.progetto_BD2.model.Genres;
import com.DiVaioCifarelli.progetto_BD2.model.Production_countries;
import com.DiVaioCifarelli.progetto_BD2.model.ResultStatics;
import com.DiVaioCifarelli.progetto_BD2.repository.CreditsRepository;
import com.DiVaioCifarelli.progetto_BD2.repository.FilmRepository;





@RestController
@RequestMapping("/api")
public class FilmController {
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	@Autowired
	FilmRepository filmRepository;
	@Autowired
	CreditsRepository creditsRepository;
	
	@GetMapping("/prova")
	public ResponseEntity<List<Film>> getByTitle(@RequestParam(required = true) String title){
		try {
		    List<Film> elenco = new ArrayList<Film>();
	    	elenco = filmRepository.findByTitle(title);
		    if (elenco.isEmpty()) {
		    	System.out.println("is empty");
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    System.out.println(elenco.size());
		    return new ResponseEntity<>(elenco, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	public MongoOperations getMongoConnection() {
		return (MongoOperations) new AnnotationConfigApplicationContext(MongoConfig.class)
                .getBean("mongoTemplate");
	}
	
	@GetMapping("/statics")
	public ResponseEntity<List<ResultStatics>> produceStatics(@RequestParam(required = true) String axis, 
			@RequestParam(required = false) String genere_1, @RequestParam(required = false) String genere_2,
			@RequestParam(required = false) String genere_3, @RequestParam(required = false) String genere_4,
			@RequestParam(required = false) String genere_5){
		List<ResultStatics> value_return = new ArrayList<>();
		List<String> generi_selezionati = new ArrayList<>();
		
		if(genere_1 != null) generi_selezionati.add(genere_1);
		if(genere_2 != null) generi_selezionati.add(genere_2);
		if(genere_3 != null) generi_selezionati.add(genere_3);
		if(genere_4 != null) generi_selezionati.add(genere_4);
		if(genere_5 != null) generi_selezionati.add(genere_5);
		
		MongoClient mongoClient = new MongoClient(
    		    new MongoClientURI(
    		        "mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false"
    		    )
    		);
    	MongoClient mongoClient1 = new MongoClient(
    		    new MongoClientURI(
    		        "mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false"
    		    )
    		);
		MongoDatabase database = mongoClient1.getDatabase("Prova");
		MongoCollection<Document> collection = database.getCollection("Film_json");
		
		try {
////		    
//			System.out.println(axis);
//			System.out.println(genere_1);
//			System.out.println(genere_2);
//			System.out.println(genere_3);
//			System.out.println(genere_4);
//			System.out.println(genere_5);
////			
		    if(axis.equals("vote_avg")) {
    	   		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$unwind", 
    		    new Document("path", "$genres")), 
    		    new Document("$group", 
    		    new Document("_id", "$genres.name")
    		            .append("avg_vote", 
    		    new Document("$avg", "$vote_average")))));
	    		value_return = retValues(generi_selezionati, result);
	    		for(ResultStatics a : value_return) {
	    			System.out.println("genere: " + a.getGenre() + " - avg: " + a.getAxis());
	    		}	
		    }else {
		    	AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$unwind", 
		    		    new Document("path", "$genres")), 
		    		    new Document("$group", 
		    		    new Document("_id", "$genres.name")
		    		            .append("rev_avg", 
		    		    new Document("$avg", "$revenue")))));
		    	value_return = retValues_rev(generi_selezionati, result);
	    		for(ResultStatics a : value_return) {
	    			System.out.println("genere: " + a.getGenre() + " - avg_rev: " + a.getAxis());
	    		}	
		    	
		    }
		    if (value_return.isEmpty()) {
		    	System.out.println("is empty");
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }else {
//		    	for(ResultStatics r : value_return) System.out.println(r.getGenre() + " - " + r.getAxis());
			    return new ResponseEntity<>(value_return, HttpStatus.OK);
		    }
		  } catch (Exception e) {
			  System.out.println("exception");
			  System.out.println(e.getCause());
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	

	public List<ResultStatics> retValues(List<String> generi, AggregateIterable<Document> res_query) {
		List<ResultStatics> temp_list = new ArrayList<>();
		List<ResultStatics> results = new ArrayList<>();
		ResultStatics temp;
		Document doc_query;
		for(Document d:res_query) {
			if(generi.contains((String)d.get("_id"))) {
				String fl = df.format((d.get("avg_vote")));
				temp = new ResultStatics((String)d.get("_id"), fl);
				results.add(temp);
			}
		}
		return results;
	}
	
	public List<ResultStatics> retValues_rev(List<String> generi, AggregateIterable<Document> res_query) {
		List<ResultStatics> temp_list = new ArrayList<>();
		List<ResultStatics> results = new ArrayList<>();
		ResultStatics temp;
		Document doc_query;
		for(Document d:res_query) {
			if(generi.contains((String)d.get("_id"))) {
				String fl = df.format((d.get("rev_avg")));
				temp = new ResultStatics((String)d.get("_id"), fl);
				results.add(temp);
			}
		}
		return results;
	}
	
	@GetMapping("/listFilm")
	public ResponseEntity<List<Film>> getByCognome(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String genres,
			@RequestParam(required = false) String cast_component,
			@RequestParam(required = false) String crew_component){
		try {
			List<Film> films = new ArrayList<Film>();
			
			List<Film> title_genres = new ArrayList<Film>();
			List<Credits> crew_cast = new ArrayList<Credits>();
//			System.out.println(title);
//			System.out.println(genres);
//			System.out.println(cast_component);
//			System.out.println(crew_component);
			
			if(title != null && genres != null) {
				//query con title e genres
				filmRepository.findByTitleAndGenres(title, genres).forEach(title_genres::add);
			}else {
				if(title == null) {
					//query con genres
					if(genres != null)filmRepository.findByGenres(genres).forEach(title_genres::add);
				}else {
					if(genres == null) {
						//query con title
						filmRepository.findByTitle(title).forEach(title_genres::add);
					}
				}
			}
			
			if(cast_component != null && crew_component != null) {
				//query con crew e cast
				creditsRepository.findByCastAndCrew(cast_component, crew_component).forEach(crew_cast::add);
			}else {
				if(cast_component == null) {
					if(crew_component!=null) creditsRepository.findByCrew(crew_component).forEach(crew_cast::add);;
					}else {
						if(crew_component == null) {
							//query con cast_component
							creditsRepository.findByCast(cast_component).forEach(crew_cast::add);
						}
					}
				}
			
			
			if(!(title_genres.isEmpty()) && !(crew_cast.isEmpty())) {
				//join
				for(Film f : title_genres) {
					if(Intersect(f, crew_cast)) films.add(f);
				}
			}else {
				if(title_genres.isEmpty()) films = getFilms(crew_cast);
				else films = title_genres;
			}
			
		    if (films.isEmpty()) {
		    	System.out.println("is empty");
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    
		    String temp = films.get(0).release_date;
		    String year = new String();
		    for(int i = 0; i<4; i++) {
		    	year += temp.charAt(i);
		    }
		    
		    
		    return new ResponseEntity<>(films, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	
	public List<Film> getFilms(List<Credits> cred){
		List<Film> lista = new ArrayList<Film>();
		Film f;
		for(Credits c : cred) {
			f = (filmRepository.findByTitle(c.getTitle())).get(0);
			lista.add(f);
		}
		return lista;
	}
	
	public boolean Intersect(Film f, List<Credits> c) {
		for(Credits cr: c) {
			if(cr.getTitle().equals(f.getTitle())) return true;
		}
		return false;
	}
}

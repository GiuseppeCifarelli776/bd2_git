package com.DiVaioCifarelli.progetto_BD2.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/statics")
	public ResponseEntity<List<ResultStatics>> produceStatics(@RequestParam(required = true) String axis, 
			@RequestParam(required = false) String genere_1, @RequestParam(required = false) String genere_2,
			@RequestParam(required = false) String genere_3, @RequestParam(required = false) String genere_4,
			@RequestParam(required = false) String genere_5){
		List<ResultStatics> value_return = new ArrayList<>();
		System.out.println("statistiche");
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
		    	//calcolo voto medio per tutti i generi
		    	if(genere_1 != null && !(genere_1.equals("null"))) {
		    		value_return.add(calcStatsRateAvg(genere_1));
		    	}
		    	
		    	if(genere_2 != null && !(genere_2.equals("null"))) { 
		    		value_return.add(calcStatsRateAvg(genere_2));
		    	}
		    	
		    	if(genere_3 != null && !(genere_3.equals("null"))) { 
		    		value_return.add(calcStatsRateAvg(genere_3));
		    	}
		    	
		    	if(genere_4 != null && !(genere_4.equals("null"))) { 
		    		value_return.add(calcStatsRateAvg(genere_4));
		    	}
		    	
		    	if(genere_5 != null && !(genere_5.equals("null"))) { 
		    		value_return.add(calcStatsRateAvg(genere_5));
		    	}
		    	
		    }else {
		    	//calcolo incasso medio per tutti i generi
		    	if(genere_1 != null && !(genere_1.equals("null"))) {
		    		value_return.add(calcStatsRevAvg(genere_1));
		    	}
		    	
		    	if(genere_2 != null && !(genere_2.equals("null"))) {
		    		value_return.add(calcStatsRevAvg(genere_2));
		    	}
		    	
		    	if(genere_3 != null && !(genere_3.equals("null"))) {
		    		value_return.add(calcStatsRevAvg(genere_3));
		    	}
		    	
		    	if(genere_4 != null && !(genere_4.equals("null"))) {
		    		value_return.add(calcStatsRevAvg(genere_4));
		    	}
		    	
		    	if(genere_5 != null && !(genere_5.equals("null"))) {
		    		value_return.add(calcStatsRevAvg(genere_5));
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
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	public ResultStatics calcStatsRateAvg(String genere) {
		ResultStatics object_return;
		//chiamare il finder per tutti i generi effettuando i controlli
    	List<Film> films = filmRepository.findByGenres(genere);
    	List<Float> temp = new ArrayList<Float>();
    	Float sum = new Float(0);
    	for(Film f : films) {
    		temp.add(Float.valueOf(f.getVote_average()));
    	}
    	for(Float d : temp) sum += d;
    	
    	String result = df.format((sum/temp.size()));
    	//System.out.println("Media : " + result);
    	
    	object_return = new ResultStatics(genere, result);
    	System.out.println(object_return.getGenre() + " - " + object_return.getAxis());
		return object_return;
	}
	
	public ResultStatics calcStatsRevAvg(String genere) {
		System.out.println("genere in funzione : " + genere );
		ResultStatics object_return;
		//chiamare il finder per tutti i generi effettuando i controlli
    	List<Film> films = filmRepository.findByGenres(genere);
    	List<Float> temp = new ArrayList<Float>();
    	Float sum = new Float(0);
    	for(Film f : films) {
    		temp.add(Float.valueOf(f.getRevenue()));
    	}
    	for(Float d : temp) sum += d;
    	
    	String result = df.format((sum/temp.size()));
    	
    	object_return = new ResultStatics(genere, result);
		return object_return;
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

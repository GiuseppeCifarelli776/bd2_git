package com.DiVaioCifarelli.progetto_BD2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DiVaioCifarelli.progetto_BD2.model.Credits;
import com.DiVaioCifarelli.progetto_BD2.model.Film;
import com.DiVaioCifarelli.progetto_BD2.model.ResultTable;
import com.DiVaioCifarelli.progetto_BD2.repository.CreditsRepository;
import com.DiVaioCifarelli.progetto_BD2.repository.FilmRepository;





@RestController
@RequestMapping("/api")
public class FilmController {
	
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

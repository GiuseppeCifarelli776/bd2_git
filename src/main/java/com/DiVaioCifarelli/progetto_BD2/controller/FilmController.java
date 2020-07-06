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
import com.DiVaioCifarelli.progetto_BD2.repository.CreditsRepository;
import com.DiVaioCifarelli.progetto_BD2.repository.FilmRepository;




@RestController
@RequestMapping("/api")
public class FilmController {
	
	@Autowired
	FilmRepository filmRepository;
	@Autowired
	CreditsRepository creditsRepository;
	
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
			
			if(title != null && genres != null) {
				System.out.println("1");
				//query con title e genres
				filmRepository.findByTitleAndGenres(title, genres).forEach(title_genres::add);
			}else {
				if(title == null) {
					//query con genres
					System.out.println("2");
					if(genres != null)filmRepository.findByGenres(genres).forEach(title_genres::add);
				}else {
					if(genres == null) {
						//query con title
						System.out.println("3");
						filmRepository.findByTitle(title).forEach(title_genres::add);
						System.out.println("uscito");
					}
				}
			}
			
			if(cast_component != null && crew_component != null) {
				//query con crew e cast
				System.out.println("4");
				creditsRepository.findByCastAndCrew(cast_component, crew_component).forEach(crew_cast::add);
			}else {
				if(cast_component == null) {
					System.out.println("5");
					if(crew_component!=null) creditsRepository.findByCrew(crew_component).forEach(crew_cast::add);;
					}else {
						if(crew_component == null) {
							System.out.println("6");
							//query con cast_component
							creditsRepository.findByCast(cast_component).forEach(crew_cast::add);
						}
					}
				}
			
			if(!(title_genres.isEmpty()) && !(crew_cast.isEmpty())) {
				System.out.println("7");
				//join
				for(Film f : title_genres) {
					System.out.println("8");
					if(Intersect(f, crew_cast)) films.add(f);
				}
			}else {
				if(title_genres.isEmpty()) films = getFilms(crew_cast);
				else films = title_genres;
			}
			
			
			
			for(Film f:films) {
				System.out.println(f.getTitle());
			}
			System.out.println(films.size());
			
			
			
		    if (films.isEmpty()) {
		    	System.out.println("is empty");
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    System.out.println("ok");
		    return new ResponseEntity<>(films, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	
	public List<Film> getFilms(List<Credits> cred){
		List<Film> lista = new ArrayList<Film>();
		for(Credits c : cred) {
			lista = filmRepository.findByTitle(c.getTitle());
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

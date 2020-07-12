package com.DiVaioCifarelli.progetto_BD2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.DiVaioCifarelli.progetto_BD2.model.Film;

public interface FilmRepository extends MongoRepository<Film, String> {

	@Query(value="{\"title\" : {$regex : \"?0\"}}")
	List<Film> findByTitle(String title);
	@Query(value="{\"genres.name\" : {$regex : \"?0\"}}") 
	List<Film> findByGenres(String genres);
	List<Film> findByTitleAndGenres(String title, String genres);
}

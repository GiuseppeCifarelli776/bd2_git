package com.DiVaioCifarelli.progetto_BD2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.DiVaioCifarelli.progetto_BD2.model.Film;

public interface FilmRepository extends MongoRepository<Film, String> {

	List<Film> findByTitle(String title);
	List<Film> findByGenres(String genres);
	List<Film> findByTitleAndGenres(String title, String genres);
	
}

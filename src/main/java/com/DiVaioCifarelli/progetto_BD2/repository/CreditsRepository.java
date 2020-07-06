package com.DiVaioCifarelli.progetto_BD2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.DiVaioCifarelli.progetto_BD2.model.Credits;

public interface CreditsRepository extends MongoRepository<Credits, String> {
	
	List<Credits> findByCast(String name); // find by name of an actor
	List<Credits> findByCrew(String name); // find by name of a director
//	List<Credits> findByTitleCrew(String title, String name);
	List<Credits> findByCastAndCrew(String name_cast, String name_crew);

}

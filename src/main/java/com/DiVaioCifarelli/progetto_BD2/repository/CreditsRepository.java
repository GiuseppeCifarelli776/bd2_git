package com.DiVaioCifarelli.progetto_BD2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.DiVaioCifarelli.progetto_BD2.model.Credits;

public interface CreditsRepository extends MongoRepository<Credits, String> {
	
	@Query(value="{\"cast\" : {$regex : ?0}}")
	List<Credits> findByCast(String name); // find by name of an actor
	@Query(value="{\"crew\" : {$regex : ?0}}")
	List<Credits> findByCrew(String name); // find by name of a director
	@Query(value="{\"cast\" : {$regex : ?0}, \"crew\" : {$regex : ?1}}")
	List<Credits> findByCastAndCrew(String name_cast, String name_crew);
	List<Credits> findByTitle(String title);

}

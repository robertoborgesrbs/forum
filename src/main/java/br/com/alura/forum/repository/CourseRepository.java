package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.model.Course;

public interface CourseRepository extends Repository<Course, Long> {
//	@Query("select c from Course c where c.name like :nome")
	
	
	
	Course findByNameStartsWith(String name);
}

package com.project.GloceryApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.GloceryApp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByName(String name);

}

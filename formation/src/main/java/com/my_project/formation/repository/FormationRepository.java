package com.my_project.formation.repository;

import com.my_project.formation.model.Formation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends CrudRepository<Formation, Integer> {

}

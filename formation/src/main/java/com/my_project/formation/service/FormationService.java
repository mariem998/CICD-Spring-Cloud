package com.my_project.formation.service;


import com.my_project.formation.model.Formation;
import com.my_project.formation.repository.FormationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FormationService {
    @Autowired
    private FormationRepository formationRepository;


    public Formation createFormation(Formation formation) {return formationRepository.save(formation);}

    public List<Formation> getAllFormations() {
        return (List<Formation>) formationRepository.findAll();
    }
    public Optional<Formation> getFormationById(int id){
        return formationRepository.findById(id);
    }
    public Formation updateFormation(Formation formation){
        if(!formationRepository.existsById(formation.getId())){
            throw new NoSuchElementException("No Formation!");
        }
        return formationRepository.save(formation);
    }
    public void deleteFormationById(Integer id)
    {
        formationRepository.deleteById(id);
    }
}

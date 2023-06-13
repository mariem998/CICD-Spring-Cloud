package com.my_project.formation.controler;



import com.my_project.formation.model.Formation;
import com.my_project.formation.service.FormationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/formations")
public class FormationController {
    @Autowired
    private FormationService formationService;

    @PostMapping
    public Formation CreateFormation(@RequestBody Formation formation){
        return formationService.createFormation(formation);}

    @GetMapping("")
    public List<Formation> getAllFormations() {
        return formationService.getAllFormations();
    }

    @DeleteMapping("/deleteFormation/{id}")
    public String deleteFormation(@PathVariable("id") Integer id)
    {
        formationService.deleteFormationById(id);
        return "Deleted";
    }

    @GetMapping("/formation/{id}")
    public Optional<Formation> getFormationById(@PathVariable("id") int formationId){
        return formationService.getFormationById(formationId);
    }

    @PutMapping("/update")
    public void updateFormation(@RequestBody Formation formation){
        formationService.updateFormation(formation);
    }

}

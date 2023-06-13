package com.my_project.formation.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Formation {
    @Id
    @SequenceGenerator(
            name = "formation_id_sequence",
            sequenceName = "formation_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "formation_id_sequence"
    )
    private Integer id;
    private String name;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endDate;

}

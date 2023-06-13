package com;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Salaire {

    @Id
    @SequenceGenerator(
            name = "salaire_id_sequence",
            sequenceName = "salaire_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "salaire_id_sequence"
    )
    private Long id;
    private String job;
    private String salary;

}

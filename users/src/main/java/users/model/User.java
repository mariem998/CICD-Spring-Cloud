package users.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "user_table")
public class User {
    @Id
    private Integer matricule ;
    @NotBlank(message = "name is required")
    private String nom ;
    @NotBlank(message = "prename is required")
    private String prenom ;

    @NotBlank(message = "email is required")
    @Email
    private String email;
    private String telephone;
    private String adresse;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password ;


}

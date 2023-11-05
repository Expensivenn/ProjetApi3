package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIEMPLOYE", schema = "ORA3", catalog = "ORCL.CONDORCET.BE")
public class Employe {
    // Les champs de la classe Employe.
    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "employe_generator")
    @SequenceGenerator(name="employe_generator", sequenceName =
            "EMPLOYE_SEQ", allocationSize=1)
    private Integer id_employe;
    @NonNull
    private String email;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    @NonNull
    private Integer id_bureau;
    @NonNull
    @JsonIgnore
    @OneToMany(mappedBy ="employe")
    @ToString.Exclude
    private List<Message> messages;

}

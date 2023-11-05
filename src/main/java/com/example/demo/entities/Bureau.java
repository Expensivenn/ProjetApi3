package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIBUREAU", schema = "ORA3", catalog = "ORCL.CONDORCET.BE")

public class Bureau {
    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "bureau_generator")
    @SequenceGenerator(name="bureau-generator", sequenceName =
            "BUREAU_SEQ", allocationSize=1)
    private Integer id_bureau;
    @NonNull
    private String sigle;
    @NonNull
    private String tel;
}

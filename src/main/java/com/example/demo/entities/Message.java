package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIMESSAGE", schema = "ORA3", catalog = "ORCL.CONDORCET.BE")
public class Message {
    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "message_generator")
    @SequenceGenerator(name="message_generator", sequenceName =
            "APIMESSAGE_SEQ3", allocationSize=1)
    private Integer id_message;
    @NonNull
    private String objet;
    private String contenu;
    private Date dateenvoi;
    //@NonNull
    //private Integer id_employe; // celui quienvoie le message
    private Integer id_message_parent; // message dont il est la reponse

    @ManyToOne @JoinColumn(name = "id_employe")
    private Employe employe;


}

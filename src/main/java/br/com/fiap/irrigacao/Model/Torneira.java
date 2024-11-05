package br.com.fiap.irrigacao.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


@Table(name = "torneira")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Torneira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_torneira")
    private Long idTorneira;
    
    @Column(name = "status_torneira")
    private String statusTorneira;
    
    @Column(name = "id_local")
    private int idLocal;


    public void setStatusTorneira(String statusTorneira){
        this.statusTorneira = statusTorneira;
    }


}

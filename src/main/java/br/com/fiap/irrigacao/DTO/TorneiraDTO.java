package br.com.fiap.irrigacao.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TorneiraDTO {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " torneira_seq")
    //@SequenceGenerator(name = " torneira_seq", sequenceName = " torneira_seq", allocationSize = 1)
    private Long idTorneira;
    private String statusTorneira;

    @NotNull
    private int idLugar;
}

package br.com.fiap.irrigacao.DTO;

import br.com.fiap.irrigacao.Model.Torneira;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TorneiraExibicaoDTO {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " torneira_seq")
    //@SequenceGenerator(name = " torneira_seq", sequenceName = " torneira_seq", allocationSize = 1)

    private Long idTorneira;

    private String statusTorneira;
    @NotNull
    private int idLugar;

    public TorneiraExibicaoDTO(Torneira torneira){
        this.setIdTorneira(torneira.getIdTorneira());
        this.setStatusTorneira(torneira.getStatusTorneira());
        this.setIdLugar(torneira.getIdLugar());
    }
}

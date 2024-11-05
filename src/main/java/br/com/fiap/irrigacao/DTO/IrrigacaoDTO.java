package br.com.fiap.irrigacao.DTO;

import br.com.fiap.irrigacao.Model.Irrigacao;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class IrrigacaoDTO {
    public IrrigacaoDTO(Irrigacao irrigacao){
        this.setId(irrigacao.getId());
        this.setData(irrigacao.getData());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date data;

}

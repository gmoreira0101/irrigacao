package br.com.fiap.irrigacao.Repository;

import br.com.fiap.irrigacao.Model.Irrigacao;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class IrrigacaoRepository implements JpaRepository<Irrigacao, Long> {
}

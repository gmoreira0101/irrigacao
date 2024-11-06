package br.com.fiap.irrigacao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.irrigacao.Model.Torneira;

public interface TorneiraRepository extends JpaRepository<Torneira, Long>{

}

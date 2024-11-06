package br.com.fiap.irrigacao.Services;

import br.com.Exception.TorneiraNaoEncontradoException;
import br.com.fiap.irrigacao.DTO.TorneiraDTO;
import br.com.fiap.irrigacao.DTO.TorneiraExibicaoDTO;
import br.com.fiap.irrigacao.Model.StatusTorneira;
import br.com.fiap.irrigacao.Model.Torneira;
import br.com.fiap.irrigacao.Repository.TorneiraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TorneiraService {

    @Autowired
    private TorneiraRepository torneiraRepository;

    public ResponseEntity criar(TorneiraDTO torneiraDTO){

        Torneira torneira = new Torneira();
        BeanUtils.copyProperties(torneiraDTO, torneira);

        torneira.setStatusTorneira(String.valueOf(StatusTorneira.Desligado));

        Torneira torneiraCriada = torneiraRepository.save(torneira);

        return new ResponseEntity( new TorneiraExibicaoDTO(torneiraCriada), HttpStatus.CREATED);

    }

    public TorneiraExibicaoDTO buscarPorIdTorneira(Long idTorneira){
        Optional<Torneira> torneiraOptional = torneiraRepository.findById(idTorneira);

        if (torneiraOptional.isPresent()){
            return new TorneiraExibicaoDTO(torneiraOptional.get());
        } else {
            throw new TorneiraNaoEncontradoException(String.format("Torneira com id %s não existe!", idTorneira));
        }
    }

    public List<TorneiraExibicaoDTO> exibirTodasAsTorneiras(){
        return torneiraRepository
                .findAll()
                .stream()
                .map(TorneiraExibicaoDTO::new)
                .toList();
    }

    public ResponseEntity excluir(Long idTorneira){

        Optional<Torneira> torneiraOptional = torneiraRepository.findById(idTorneira);

        if (torneiraOptional.isPresent()){
            torneiraRepository.delete(torneiraOptional.get());
            return ResponseEntity.accepted().body(String.format("Torneira com ID %s deletada! :D", idTorneira));
        } else {
            throw new TorneiraNaoEncontradoException(String.format("Pedido %s não existe!", idTorneira));
        }

    }

    public ResponseEntity<TorneiraExibicaoDTO> atualizar(TorneiraDTO torneiraDTO) {
        var id = torneiraDTO.getIdTorneira();
        Optional<Torneira> torneiraOptional = torneiraRepository.findById(id.longValue());

        Torneira torneiraAtualizada;
        if (torneiraOptional.isPresent()) {
            Torneira torneira = new Torneira();
            BeanUtils.copyProperties(torneiraDTO, torneira);
            torneiraAtualizada = torneiraRepository.save(torneira);
            return ResponseEntity.ok().body(new TorneiraExibicaoDTO(torneiraAtualizada));

        } else {
            throw new TorneiraNaoEncontradoException(String.format("Torneira com id %s não existe!", torneiraDTO.getIdTorneira()));
        }
    }

}

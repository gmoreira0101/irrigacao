package br.com.fiap.irrigacao.Services;

import br.com.fiap.irrigacao.DTO.IrrigacaoDTO;
import br.com.fiap.irrigacao.Mappers.Mapper;
import br.com.fiap.irrigacao.Model.Irrigacao;
import br.com.fiap.irrigacao.Repository.IrrigacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class IrrigacaoService {
    @Autowired
    private IrrigacaoRepository repository;
    private Mapper mapper;

    public Page<IrrigacaoDTO> getAll(Pageable paginacao){
        try{
            return repository.findAll(paginacao).map(new Function<Irrigacao, IrrigacaoDTO>() {
                @Override
                public IrrigacaoDTO apply(Irrigacao irrigacao) {
                    var dto = new IrrigacaoDTO(irrigacao);
                    return dto;
                }
            });
        }
        catch (Exception e){
            throw e;
        }

    }

    public ResponseEntity createIrrigacao(Irrigacao irrigacao) throws URISyntaxException {
        try{
            repository.save(irrigacao);
            return new ResponseEntity(new IrrigacaoDTO(irrigacao), HttpStatus.CREATED);
        }
        catch (Exception e){
            throw e;
        }

    }

    public ResponseEntity putIrrigacao(Irrigacao irrigacao){
        try{
            var oldIrrigacao = repository.findById(irrigacao.getId())
                    .orElseThrow(() ->
                            new EntityNotFoundException(String.format("Irrigacação com ID {0} não encontrada", irrigacao.getId())));
            return ResponseEntity.ok().body(repository.save(irrigacao));
        }
        catch (DataAccessException dataAccessException){
            return ResponseEntity.internalServerError().body("Erro ao conectar com o banco de dados");
        }
    }

    public ResponseEntity deleteIrrigacao(int id){
        try {
            return ResponseEntity.accepted().body(String.format("Irrigação com ID {0} deletada", id));
        }
        catch (Exception e){
            throw e;
        }
    }
}

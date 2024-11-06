package br.com.fiap.irrigacao.Controllers;

import br.com.fiap.irrigacao.DTO.TorneiraDTO;
import br.com.fiap.irrigacao.DTO.TorneiraExibicaoDTO;
import br.com.fiap.irrigacao.Model.Torneira;
import br.com.fiap.irrigacao.Services.TorneiraService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/irrigacao")
public class TorneiraController {
    @Autowired
    private TorneiraService service;

    @GetMapping()
    public List<TorneiraExibicaoDTO> getAllIrrigacoes(){
        return service.exibirTodasAsTorneiras();
    }

    @GetMapping("/{id}")
    public TorneiraExibicaoDTO getTorneiraById(@PathVariable("id") long id){
        return service.buscarPorIdTorneira(id);
    }

    @PostMapping
    public ResponseEntity createIrrigacao(@RequestBody @Valid TorneiraDTO torneira, UriComponentsBuilder uriBuilder) throws URISyntaxException {
        return service.criar(torneira);
    }

    @PutMapping
    public ResponseEntity putIrrigacao(@RequestBody @Valid TorneiraDTO irrigacao, UriComponentsBuilder uriBuilder){
        return service.atualizar(irrigacao);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteIrrigacao(@PathVariable("id") Long id){
        return service.excluir(id);
    }
}

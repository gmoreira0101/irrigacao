package br.com.fiap.irrigacao.Controllers;

import br.com.fiap.irrigacao.DTO.IrrigacaoDTO;
import br.com.fiap.irrigacao.Model.Irrigacao;
import br.com.fiap.irrigacao.Services.IrrigacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/irrigacao")
public class IrrigacaoController {
    @Autowired
    private IrrigacaoService service;

    @GetMapping()
    public Page<IrrigacaoDTO> getAllIrrigacoes(@PageableDefault(size = 10) Pageable paginacao){
        return service.getAll(paginacao);
    }

    @PostMapping
    public ResponseEntity createIrrigacao(@RequestBody @Valid Irrigacao irrigacao, UriComponentsBuilder uriBuilder) throws URISyntaxException {
        return service.createIrrigacao(irrigacao);
    }

    @PutMapping
    public ResponseEntity putIrrigacao(@RequestBody @Valid Irrigacao irrigacao, UriComponentsBuilder uriBuilder){
        return service.putIrrigacao(irrigacao);
    }

    @DeleteMapping
    public ResponseEntity deleteIrrigacao(@RequestParam int id){
        return service.deleteIrrigacao(id);
    }
}

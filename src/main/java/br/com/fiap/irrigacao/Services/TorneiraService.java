package br.com.fiap.pedidos.service;

import br.com.fiap.irrigacao.DTO.TorneiraDTO;
import br.com.fiap.irrigacao.DTO.TorneiraExibicaoDto;
import br.com.fiap.irrigacao.Exception.TorneiraNaoEncontradoException;
import br.com.fiap.irrigacao.Model.StatusTorneira;
import br.com.fiap.irrigacao.Model.Torneira;
import br.com.fiap.irrigacao.Repository.TorneiraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TorneiraService {

    @Autowired
    private TorneiraRepository torneiraRepository;

    public TorneiraExibicaoDto criar(TorneiraDTO torneiraDTO){

        Torneira torneira = new Torneira();
        BeanUtils.copyProperties(torneiraDTO, torneira);

        Torneira.setStatusTorneira(StatusTorneira.Desligado);
        Torneira torneiraCriado = torneiraRepository.save(torneira);

        return new TorneiraExibicaoDto(torneiraCriado);

    }

    public TorneiraExibicaoDto buscarPorIdTorneira(Long idTorneira){
        Optional<Torneira> pedidoOptional = TorneiraRepository.findById(idTorneira);

        if (pedidoOptional.isPresent()){
            return new PedidoExibicaoDto(pedidoOptional.get());
        } else {
            throw new PedidoNaoEncontradoException(String.format("Pedido %s não existe!", numeroPedido));
        }
    }

    public List<PedidoExibicaoDto> exibirTodosOsPedidos(){
        return pedidoRepository
                .findAll()
                .stream()
                .map(PedidoExibicaoDto::new)
                .toList();
    }

    public void excluir(Long numeroPedido){

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(numeroPedido);

        if (pedidoOptional.isPresent()){
            pedidoRepository.delete(pedidoOptional.get());
        } else {
            throw new PedidoNaoEncontradoException(String.format("Pedido %s não existe!", numeroPedido));
        }
        Pedido pedido = new Pedido();

    }

    public PedidoExibicaoDto atualizar(PedidoDto pedidoDto){
        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDto, pedido);
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return new PedidoExibicaoDto(pedidoAtualizado);
    }

}

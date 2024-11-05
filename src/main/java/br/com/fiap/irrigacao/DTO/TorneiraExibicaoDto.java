package br.com.fiap.irrigacao.DTO;

public record TorneiraExibicaoDto(
    private Integer idTorneira,
    private String statusTorneira,
    private int idLocal,
    
) { public TorneiraExibicaoDTO(Torneira torneira){
        this(
            torneira.getIdTorneira()
            torneira.getStatusTorneira(),
            torneira.getIdLocal(),
        )
    }
};


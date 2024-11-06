package br.com.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TorneiraNaoEncontradoException extends RuntimeException {
    
    public TorneiraNaoEncontradoException(String mensagem){
        super(mensagem);
    }

}

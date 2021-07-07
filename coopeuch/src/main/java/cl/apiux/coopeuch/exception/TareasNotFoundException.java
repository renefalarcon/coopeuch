package cl.apiux.coopeuch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TareasNotFoundException extends RuntimeException{

    public TareasNotFoundException(String mensaje) {
        super(mensaje);
    }
}

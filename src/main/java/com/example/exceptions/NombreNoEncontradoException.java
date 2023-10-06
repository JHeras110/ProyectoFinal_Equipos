package com.example.exceptions;

public class NombreNoEncontradoException extends RuntimeException{
    public NombreNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

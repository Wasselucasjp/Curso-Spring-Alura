package br.com.alura.forum.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alura.forum.dto.ErrorDeFormularioDTO;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
    /**
     * Classe controladora que lida com exceções do tipo
     * MethodArgumentNotValidException,
     * retornando uma lista de objetos ErrorDeFormularioDTO representando os erros
     * de validação
     * nos campos de um formulário.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Método que trata a exceção MethodArgumentNotValidException e retorna uma
     * lista de objetos
     * ErrorDeFormularioDTO contendo informações sobre os erros de validação nos
     * campos do formulário.
     *
     * @param exception Exceção do tipo MethodArgumentNotValidException a ser
     *                  tratada.
     * @return Lista de objetos ErrorDeFormularioDTO contendo detalhes sobre os
     *         erros de validação.
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDeFormularioDTO> handle(MethodArgumentNotValidException exception) {
        List<ErrorDeFormularioDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        // Itera sobre os erros de campo e adiciona informações ao objeto
        // ErrorDeFormularioDTO
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorDeFormularioDTO erro = new ErrorDeFormularioDTO(e.getField(), mensagem);
            dto.add(erro);
        });

        return dto;
    }
}

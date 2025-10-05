package br.edu.infinet.sergiotransacoesapi.controller.exception;

import br.edu.infinet.sergiotransacoesapi.model.domain.exceptions.ExternalServiceException;
import br.edu.infinet.sergiotransacoesapi.model.domain.exceptions.ResourceInvalidException;
import br.edu.infinet.sergiotransacoesapi.model.domain.exceptions.ResourceNotFoundException;
import br.edu.infinet.sergiotransacoesapi.model.domain.exceptions.response.ApiResponseError;
import br.edu.infinet.sergiotransacoesapi.model.util.ConstanteUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var apiErro = new ApiResponseError(HttpStatus.BAD_REQUEST, request.getRequestURI());
        apiErro.setError(ConstanteUtil.ERRO_DADOS_INVALIDOS);
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                apiErro.addFieldsError(fieldError.getField(), fieldError.getDefaultMessage()));
        return buildResponseEntity(apiErro);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        var apiErro = new ApiResponseError(HttpStatus.NOT_FOUND, request.getRequestURI());
        apiErro.setError(ConstanteUtil.ERRO_RECURSO_NAO_ENCONTRADO);
        apiErro.setMessage(ex.getMessage());
        return buildResponseEntity(apiErro);
    }


    @ExceptionHandler(ResourceInvalidException.class)
    protected ResponseEntity<Object> handleResourceInvalidException(ResourceInvalidException ex, HttpServletRequest request) {
        var apiErro = new ApiResponseError(HttpStatus.BAD_REQUEST, request.getRequestURI());
        apiErro.setError(ConstanteUtil.ERRO_REQUISICAO_INVALIDA);
        apiErro.setMessage(ex.getMessage());
        return buildResponseEntity(apiErro);
    }

    @ExceptionHandler(ExternalServiceException.class)
    protected ResponseEntity<Object> handleExternalServiceException(ExternalServiceException ex, HttpServletRequest request) {
        var apiErro = new ApiResponseError(ex.getStatus(), request.getRequestURI());
        apiErro.setError(ex.getStatus().toString());
        apiErro.setMessage(ex.getMessage());
        return buildResponseEntity(apiErro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        var apiErro = new ApiResponseError(HttpStatus.CONFLICT, request.getRequestURI());
        apiErro.setError(ConstanteUtil.ERRO_CONFLITO_BANCO_DE_DADOS);
        apiErro.setMessage(ex.getMessage());
        return buildResponseEntity(apiErro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        var apiErro = new ApiResponseError(HttpStatus.BAD_REQUEST, request.getRequestURI());
        apiErro.setError(ConstanteUtil.ERRO_REQUISICAO_INVALIDA);
        apiErro.setMessage("Parâmetro inválido: " + ex.getMessage());
        return buildResponseEntity(apiErro);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        var apiErro = new ApiResponseError(HttpStatus.BAD_REQUEST, request.getRequestURI());
        apiErro.setError(ConstanteUtil.ERRO_REQUISICAO_INVALIDA);
        apiErro.setMessage("Parâmetros inválidos");
        ex.getConstraintViolations().forEach(violation -> apiErro.addFieldsError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()
                )
        );
        return buildResponseEntity(apiErro);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAll(Exception ex, HttpServletRequest request){
        var apiErro = new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
        apiErro.setError(ConstanteUtil.ERRO_INTERNO_SERVIDOR);
        apiErro.setMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiErro);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiResponseError apiErro) {
        return new ResponseEntity<>(apiErro, HttpStatus.valueOf(Integer.parseInt(apiErro.getStatus())));
    }
}
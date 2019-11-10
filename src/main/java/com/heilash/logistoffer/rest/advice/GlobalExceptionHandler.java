package com.heilash.logistoffer.rest.advice;

import com.heilash.logistoffer.domain.exception.NotFoundException;
import com.heilash.logistoffer.domain.exception.ServiceException;
import com.heilash.logistoffer.domain.service.MessageFacade;
import com.heilash.logistoffer.rest.message.BaseResponse;
import com.heilash.logistoffer.rest.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;

import static com.heilash.logistoffer.domain.exception.Errors.INTERNAL_SERVICE_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private MessageFacade messageFacade;

    @ExceptionHandler({ServiceException.class})
    public final ResponseEntity<BaseResponse> handleServiceException(ServiceException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        BaseResponse response = new BaseResponse();
        Error error = new Error();
        error.setCode(ex.getCode());
        error.setMessage(messageFacade.getMessage(ex.getCode()));
        response.setError(error);
        return new ResponseEntity<>(response, status);

    }

    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<BaseResponse> handleNotFoundException(NotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        BaseResponse response = new BaseResponse();
        Error error = new Error();
        error.setCode(ex.getCode());
        response.setError(error);
        error.setMessage(messageFacade.getMessage(ex.getCode()));
        return new ResponseEntity<>(response, status);

    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<BaseResponse> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        BaseResponse response = new BaseResponse();
       // ex.getBindingResult()
        Error error = new Error();
        error.setCode("validation.error");
        response.setError(error);
        //  error.setMessage(messageFacade.getMessage(ex.getCode()));
        return new ResponseEntity<>(response, status);

    }


    @ExceptionHandler({Throwable.class})
    public final ResponseEntity<BaseResponse> handleException(Throwable ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        BaseResponse response = new BaseResponse();
        Error error = new Error();
        error.setCode(INTERNAL_SERVICE_ERROR);
        response.setError(error);
        error.setMessage(messageFacade.getMessage(INTERNAL_SERVICE_ERROR));
        return new ResponseEntity<>(response, status);
    }
}

package ru.gontarenko.codesharingplatform.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class ExceptionHandlerController {

    @ExceptionHandler(value = { SnippetException.class })
    public String snippetExceptionHandle(SnippetException e) {
        return "not_found";
    }

    @ExceptionHandler(value = { MyUserException.class })
    public String myUserExceptionHandle(MyUserException e) {
        return "not_found";
    }
}

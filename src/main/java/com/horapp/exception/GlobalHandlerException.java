package com.horapp.exception;

import com.horapp.exception.schedule.ScheduleCreationException;
import com.horapp.exception.category.CategoryCreationException;
import com.horapp.exception.course.CourseCreationException;
import com.horapp.exception.dayAndTime.DayAndTimeCreationException;
import com.horapp.exception.feedback.FeedbackCreationException;
import com.horapp.exception.major.MajorCreationException;
import com.horapp.exception.time_table.TimeTableCreationException;
import com.horapp.exception.user.UserCreationException;
import com.horapp.presentation.dto.response.exception.ExceptionResponse;
import com.horapp.presentation.dto.response.exception.ValidationExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class GlobalHandlerException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> genericHandler(Exception exception, HttpServletRequest request){
        logger.error("Generic handler exception :".concat(exception.getClass().getName()));
        logger.error("Message: ".concat(exception.getMessage()));
        logger.error("Stack trace: ", exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(NotFoundException exception, HttpServletRequest request) {
        logger.error("Resource not found: ", exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(MajorCreationException.class)
    public ResponseEntity<ExceptionResponse> majorCreationException(MajorCreationException exception, HttpServletRequest request){
        return getExceptionBadRequest(exception.getMessage(), request);
    }


    @ExceptionHandler(CategoryCreationException.class)
    public ResponseEntity<ExceptionResponse> categoryCreationException(CategoryCreationException exception, HttpServletRequest request){
        return getExceptionBadRequest(exception.getMessage(), request);
    }

    @ExceptionHandler(FeedbackCreationException.class)
    public ResponseEntity<ExceptionResponse> feedbackCreationException(FeedbackCreationException exception, HttpServletRequest request){
        return getExceptionBadRequest(exception.getMessage(), request);
    }

    @ExceptionHandler(DayAndTimeCreationException.class)
    public ResponseEntity<ExceptionResponse> dayAndTimeCreationException(DayAndTimeCreationException exception, HttpServletRequest request){
        return getExceptionBadRequest(exception.getMessage(), request);
    }


    @ExceptionHandler(ScheduleCreationException.class)
    public ResponseEntity<ExceptionResponse> scheduleCreationException(ScheduleCreationException exception, HttpServletRequest request){
        return getExceptionBadRequest(exception.getMessage(), request);
    }

    @ExceptionHandler(CourseCreationException.class)
    public ResponseEntity<ExceptionResponse> courseCreationException(CourseCreationException exception, HttpServletRequest request){
        return getExceptionBadRequest(exception.getMessage(), request);
    }

    @ExceptionHandler(TimeTableCreationException.class)
    public ResponseEntity<ExceptionResponse> timeTableCreationException(TimeTableCreationException exception, HttpServletRequest request){
        return getExceptionBadRequest(exception.getMessage(), request);
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ExceptionResponse> userCreationException(UserCreationException exception, HttpServletRequest request){
        return getExceptionBadRequest(exception.getMessage(), request);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException exception, HttpServletRequest request) {
        return getExceptionBadRequest(exception.getMessage(), request);
    }

    @ExceptionHandler(EmptyRequestBodyException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyRequestBodyException(
            EmptyRequestBodyException exception,
            HttpServletRequest request) {

        return getExceptionBadRequest(exception.getMessage(), request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {

        return getExceptionBadRequest("The request body is missing or malformed.", request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationExceptionResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }

        return new ValidationExceptionResponse(
                "Validation failed",
                HttpStatus.BAD_REQUEST,
                ex.getParameter().getMethod().getName(),
                ex.getParameter().getContainingClass().getName(),
                validationErrors
        );
    }

    private static ResponseEntity<ExceptionResponse> getExceptionBadRequest(String exception, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception,
                HttpStatus.BAD_REQUEST,
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }



}

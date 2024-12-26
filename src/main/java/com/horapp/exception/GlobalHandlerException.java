package com.horapp.exception;

import com.horapp.exception.schedule.ScheduleCreationException;
import com.horapp.exception.schedule.ScheduleNotFoundException;
import com.horapp.exception.category.CategoryCreationException;
import com.horapp.exception.category.CategoryNotFoundException;
import com.horapp.exception.course.CourseCreationException;
import com.horapp.exception.course.CourseNotFoundException;
import com.horapp.exception.dayAndTime.DayAndTimeCreationException;
import com.horapp.exception.dayAndTime.DayAndTimeNotFoundException;
import com.horapp.exception.feedback.FeedbackCreationException;
import com.horapp.exception.feedback.FeedbackNotFoundException;
import com.horapp.exception.major.MajorCreationException;
import com.horapp.exception.major.MajorNotFoundException;
import com.horapp.exception.time_table.TimeTableCreationException;
import com.horapp.exception.time_table.TimeTableNotFoundException;
import com.horapp.exception.user.UserCreationException;
import com.horapp.exception.user.UserNotFoundException;
import com.horapp.presentation.dto.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(MajorNotFoundException.class)
    public ResponseEntity<ExceptionResponse> majorNotFoundException(MajorNotFoundException notFoundException, HttpServletRequest request){
        return getExceptionResponseResponseEntity(notFoundException.getMessage(), request);
    }

    @ExceptionHandler(MajorCreationException.class)
    public ResponseEntity<ExceptionResponse> majorCreationException(MajorCreationException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> categoryNotFoundException(CategoryNotFoundException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(CategoryCreationException.class)
    public ResponseEntity<ExceptionResponse> categoryCreationException(CategoryCreationException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(FeedbackCreationException.class)
    public ResponseEntity<ExceptionResponse> feedbackCreationException(FeedbackCreationException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<ExceptionResponse> feedbackNotFoundException(FeedbackNotFoundException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(DayAndTimeCreationException.class)
    public ResponseEntity<ExceptionResponse> dayAndTimeCreationException(DayAndTimeCreationException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(DayAndTimeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> dayAndTimeNotFoundException(DayAndTimeNotFoundException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(ScheduleCreationException.class)
    public ResponseEntity<ExceptionResponse> scheduleCreationException(ScheduleCreationException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ExceptionResponse> scheduleNotFoundException(ScheduleNotFoundException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(CourseCreationException.class)
    public ResponseEntity<ExceptionResponse> courseCreationException(CourseCreationException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> courseNotFoundException(CourseNotFoundException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(TimeTableNotFoundException.class)
    public ResponseEntity<ExceptionResponse> timeTableNotFoundException(TimeTableNotFoundException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(TimeTableCreationException.class)
    public ResponseEntity<ExceptionResponse> timeTableCreationException(TimeTableCreationException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(UserNotFoundException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ExceptionResponse> userCreationException(UserCreationException exception, HttpServletRequest request){
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException exception, HttpServletRequest request) {
        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(EmptyRequestBodyException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyRequestBodyException(
            EmptyRequestBodyException exception,
            HttpServletRequest request) {

        return getExceptionResponseResponseEntity(exception.getMessage(), request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {

        return getExceptionResponseResponseEntity("The request body is missing or malformed.", request);
    }

    private static ResponseEntity<ExceptionResponse> getExceptionResponseResponseEntity(String exception, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception,
                HttpStatus.BAD_REQUEST,
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }

}

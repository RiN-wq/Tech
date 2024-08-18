package com.example.demo.responces;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Класс "неуспешного" ответа на запрос
 */
@Data
@Component
public class ErrorResponse implements Response {

    /** Переменная статуса успеха запроса */
    boolean status = false;
    /** Переменная сообщения об ошибки */
    String error;

}

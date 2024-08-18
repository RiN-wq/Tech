package com.example.demo.responces;


import lombok.Data;
import org.springframework.stereotype.Component;
/**
 * Класс "успешного" ответа на запрос
 */
@Data
@Component
public class SuccessfulResponse implements Response {

    /** Переменная статуса успеха запроса */
    boolean status = true;

}

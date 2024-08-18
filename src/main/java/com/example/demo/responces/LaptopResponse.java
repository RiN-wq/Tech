package com.example.demo.responces;

import com.example.demo.models.LaptopModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс "успешного" ответа на запрос со списком объектов "Laptop"
 */
@Data
@Component
public class LaptopResponse implements Response {

    /** Переменная статуса успеха запроса */
    boolean status = true;
    /** Список объектов "Laptop" */
    List<LaptopModel> laptops;

}

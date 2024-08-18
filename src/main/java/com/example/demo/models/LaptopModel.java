package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/** Модель объекта "Laptop" */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "laptop")
@Component
public class LaptopModel {
    /**
     * Переменная, отвечающая за идентификатор модели объекта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Переменная, отвечающая за имя модели объекта
     */
    @Column(name = "model_name")
    private String modelName;
    @Column(name = "release_year")
    /**
     * Переменная, отвечающая за год реализации модели объекта
     */
    private int releaseYear;
    @Column(columnDefinition = "INT NOT NULL")
    private int price;

    /** Метод, выводящий информацию о модели объекта */
    @Override
    public String toString() {
        return "{name: " + modelName + ", year: " + releaseYear + ", price: " + price + "}";
    }
}

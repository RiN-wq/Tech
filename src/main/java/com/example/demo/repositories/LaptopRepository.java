package com.example.demo.repositories;

import com.example.demo.models.LaptopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA интерфейс-репозиторий модели "LaptopModel"
 */
@Repository
public interface LaptopRepository extends JpaRepository<LaptopModel, Integer> {

    /** Метод возврата модели "LaptopModel" по параметру из БД
     * @param modelName - название модели объекта "Laptop"
     */
    LaptopModel findByModelName(String modelName);

}

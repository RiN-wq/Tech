package com.example.demo.services;

import com.example.demo.models.LaptopModel;
import com.example.demo.repositories.LaptopRepository;
import com.example.demo.responces.ErrorResponse;
import com.example.demo.responces.LaptopResponse;
import com.example.demo.responces.Response;
import com.example.demo.responces.SuccessfulResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, выполняющий CRUD-операции над объектом "Laptop"
 */

@Service
public class LaptopHandlerServiceImpl implements LaptopHandlerService {

    /** Переменная класса */
    private final static Logger logger = LoggerFactory.getLogger(LaptopHandlerServiceImpl.class);
    /** Переменная класса репозитория */
    private final LaptopRepository laptopRepository;
    /** Переменная класса модели объекта "Laptop" */
    private LaptopModel laptopModel;
    /** Переменная класса "неуспешного" ответа */
    private final ErrorResponse errorResponse;
    /** Переменная класса "успешного" ответа" */
    private final SuccessfulResponse successfulResponse;
    /** Переменная класса ответа с возвратом "успешного" ответа и списка объектов "Laptop" */
    private final LaptopResponse laptopResponse;

    @Autowired
    public LaptopHandlerServiceImpl(LaptopRepository laptopRepository,
                                    LaptopModel laptopModel,
                                    ErrorResponse errorResponse,
                                    SuccessfulResponse successfulResponse,
                                    LaptopResponse laptopResponse) {

        this.laptopRepository = laptopRepository;
        this.laptopModel = laptopModel;
        this.errorResponse = errorResponse;
        this.successfulResponse = successfulResponse;
        this.laptopResponse = laptopResponse;
    }

    /**
     * Метод, создающий запись объекта в БД
     * @param laptopModel - модель объекта "Laptop"
     * @return ErrorResponse в случае ошибки и SuccessfulResponse в случае успеха
     */
    public Response createLaptop(LaptopModel laptopModel) {

        logger.info("Обращение к методу \"createLaptop\"");

        if (laptopRepository.findByModelName(laptopModel.getModelName()) != null) {

            logger.warn("Введён дубликат модели, model: " + laptopModel.toString());
            return getSimpleResponse("Ошибка создания дубликата модели!");

        }

        logger.info("Сохранение модели, id: " + laptopModel.getId());
        laptopRepository.save(laptopModel);

        return getSimpleResponse("");

    }

    /**
     * Метод, получающий запись объекта из БД
     * @param modelName - название модели объекта "Laptop"
     * @return ErrorResponse в случае ошибки и LaptopResponse в случае успеха
     */
    public Response getLaptopByModelName(String modelName) {

        logger.info("Обращение к методу \"getLaptopByModelName\"");

        laptopModel = laptopRepository.findByModelName(modelName);

        if (laptopModel == null) {

            logger.warn("Введено несуществующие имя для получения модели, name:  " + modelName);
            return getSimpleResponse("Ошибка получения несуществующей модели!");

        }

        logger.info("Вывод модели, id: " + laptopModel.getId());
        laptopResponse.setLaptops(List.of(laptopModel));

        return laptopResponse;

    }

    /**
     * Метод, получающий все записи объектов из БД
     * @return ErrorResponse в случае ошибки и LaptopResponse в случае успеха
     */
    public Response getAllLaptops() {

        logger.info("Обращение к методу \"getAllLaptops\"");

        List<LaptopModel> laptopModelList = new ArrayList<>(laptopRepository.findAll());

        if (laptopModelList.isEmpty()) {

            logger.warn("Попытка получения моделей из пустой БД");
            return getSimpleResponse("Ошибка получения несуществующих моделей!");

        }

        logger.info("Вывод всех моделей из БД, size: " + laptopModelList.size());
        laptopResponse.setLaptops(laptopModelList);

        return laptopResponse;

    }

    /**
     * Метод, обновляющий запись объекта в таблице БД
     * @param laptopModel - название модели объекта "Laptop"
     * @return ErrorResponse в случае ошибки и SuccessfulResponse в случае успеха
     */
    public Response updateLaptop(LaptopModel laptopModel) {

        logger.info("Обращение к методу \"updateLaptop\"");

        LaptopModel existslaptopModel = laptopRepository.findByModelName(laptopModel.getModelName());

        if (existslaptopModel == null) {

            logger.warn("Попытка обновления несуществующей модели, model: " + laptopModel.toString());
            return getSimpleResponse("Ошибка обновления несуществующей модели!");

        }

        existslaptopModel.setReleaseYear(laptopModel.getReleaseYear());
        existslaptopModel.setPrice(laptopModel.getPrice());

        logger.info("Сохранение обновлённой версии модели, id: " + laptopModel.getId());
        laptopRepository.save(existslaptopModel);

        return getSimpleResponse("");

    }

    /**
     * Метод, удаляющий запись объекта из таблицы БД
     * @param modelName - название модели объекта "Laptop"
     * @return ErrorResponse в случае ошибки и SuccessfulResponse в случае успеха
     */
    public Response deleteLaptopByModelName(String modelName) {

        logger.info("Обращение к методу \"deleteLaptopByModelName\"");

        laptopModel = laptopRepository.findByModelName(modelName);

        if (laptopModel == null) {

            logger.warn("Попытка удаления несуществующей модели, name: " + modelName);
            return getSimpleResponse("Ошибка удаления несуществующей модели!");

        }

        logger.info("Удаление модели, id: " + laptopModel.getId());
        laptopRepository.delete(laptopModel);

        return getSimpleResponse("");

    }

    /**
     *
     * @param error - сообщение ошибки
     * @return successfulResponse в случае отсутствия параметра и errorResponse в случае его наличия
     */
    public Response getSimpleResponse(String error) {

        if (error.isBlank()) {
            return successfulResponse;
        } else {
            errorResponse.setError(error);
            return errorResponse;
        }

    }

}
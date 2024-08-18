package com.example.demo.controllers;

import com.example.demo.models.LaptopModel;
import com.example.demo.responces.Response;
import com.example.demo.services.LaptopHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Класс-контроллер
 */
@RestController
@RequestMapping("/laptop")
public class DefaultController {

    private final LaptopHandlerService laptopHandlerService;

    /**
     * Конструктор, делающий иньекцию класса-сервиса LaptopHandlerService
     * @param laptopHandlerService - класс-сервис, выполняющий CRUD операции над объектом "Laptop"
     */
    @Autowired
    public DefaultController(LaptopHandlerService laptopHandlerService) {
        this.laptopHandlerService = laptopHandlerService;
    }

    /**
     * POST-запрос к серверу /laptop/add, обращающийся к методу createLaptop
     *
     * @param laptopModel - модель объекта "Laptop"
     * @return ResponseEntity
     */
    @PostMapping(path = "/add")
    public ResponseEntity<Response> addLaptop(@RequestBody LaptopModel laptopModel) {
        return new ResponseEntity<>(laptopHandlerService.createLaptop(laptopModel), HttpStatus.CREATED);
    }

    /**
     * GET-запрос к серверу /laptop/get, обращающийся к методу getLaptopByModelName
     *
     * @param modelName - название модели объекта "Laptop"
     * @return ResponseEntity
     */
    @GetMapping(path = "/get")
    public ResponseEntity<Response> getLaptop(@RequestParam String modelName) {
        return new ResponseEntity<>(laptopHandlerService.getLaptopByModelName(modelName), HttpStatus.OK);
    }

    /**
     * GET-запрос к серверу /laptop/getAll, обращающийся к методу getAllLaptops
     *
     * @return ResponseEntity
     */
    @GetMapping(path = "/getAll")
    public ResponseEntity<Response> getAllLaptops() {
        return new ResponseEntity<>(laptopHandlerService.getAllLaptops(), HttpStatus.OK);
    }

    /**
     * GET-запрос к серверу /laptop/update, обращающийся к методу updateLaptop
     *
     * @param laptopModel - модель объекта "Laptop"
     * @return ResponseEntity
     */
    @PutMapping(path = "/update")
    public ResponseEntity<Response> updateLaptop(@RequestBody LaptopModel laptopModel) {
        return new ResponseEntity<>(laptopHandlerService.updateLaptop(laptopModel), HttpStatus.OK);
    }

    /**
     * DELETE-запрос к серверу /laptop/delete, обращающийся к методу deleteLaptopByModelName
     *
     * @param modelName - название модели объекта "Laptop"
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/delete")
    public ResponseEntity<Response> deleteLaptop(@RequestParam String modelName) {
        return new ResponseEntity<>(laptopHandlerService.deleteLaptopByModelName(modelName), HttpStatus.OK);
    }

}

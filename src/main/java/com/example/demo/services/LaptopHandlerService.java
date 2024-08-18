package com.example.demo.services;

import com.example.demo.models.LaptopModel;
import com.example.demo.responces.Response;

/**
 * Интерфейс, с набором методов, предназначенных для выполнения CRUD операций
 */

public interface LaptopHandlerService {

    Response createLaptop(LaptopModel laptopModel);

    Response getLaptopByModelName(String modelName);

    Response getAllLaptops();

    Response updateLaptop(LaptopModel laptopModel);

    Response deleteLaptopByModelName(String modelName);

    Response getSimpleResponse(String error);

}

package com.example.RestSensor.util;

import com.example.RestSensor.models.Sensor;
import com.example.RestSensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorService.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", "Sensor with this same is already exists");
    }
}
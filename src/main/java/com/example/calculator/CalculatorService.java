package com.example.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Double plus(Double a, Double b) {
        return a + b;
    }

    public Double minus(Double a, Double b) {
        return a - b;
    }

    public Double multiply(Double a, Double b) {
        return a * b;
    }

    public Double divide(Double a, Double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Деление на ноль невозможно.");
        }
        return a / b;
    }
}
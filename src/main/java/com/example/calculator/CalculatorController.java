package com.example.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculator")
    public String welcome() {
        return "Добро пожаловать в калькулятор!";
    }

    @GetMapping("/plus")
    public String add(@RequestParam(required = false) Double a, @RequestParam(required = false) Double b) {
        return processRequest(a, b, "сложения"  + a + " + " + b + " = ", () -> calculatorService.plus(a, b));
    }

    @GetMapping("/minus")
    public String subtract(@RequestParam(required = false) Double a, @RequestParam(required = false) Double b) {
        return processRequest(a, b, "вычитания " + a + " - " + b + " = ", () -> calculatorService.minus(a, b));
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam(required = false) Double a, @RequestParam(required = false) Double b) {
        return processRequest(a, b, "умножения"  + a + " * " + b + " = ", () -> calculatorService.multiply(a, b));
    }

    @GetMapping("/divide")
    public String divide(@RequestParam(required = false) Double a, @RequestParam(required = false) Double b) {
        return processRequest(a, b, "деления"  + a + " / " + b + " = ", () -> calculatorService.divide(a, b));
    }

    private String processRequest(Double a, Double b, String operation, Calculation calculation) {
        if (a == null || b == null) {
            return String.format("Ошибка: Для операции %s необходимо указать оба параметра 'a' и 'b'.", operation);
        }

        try {
            Double result = calculation.calculate();
            return String.format("Результат %s: %.2f", operation, result);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @FunctionalInterface
    interface Calculation {
        Double calculate();
    }
}
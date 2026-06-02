package com.example.converter.controller;

import com.example.converter.model.ConverterModel;
import com.example.converter.model.InputValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ConverterController implements Initializable {

    @FXML
    private TextField inputField;

    @FXML
    private ComboBox<String> fromUnit;

    @FXML
    private ComboBox<String> toUnit;

    @FXML
    private Label resultLabel;

    @FXML
    private Label errorLabel;

    private final ConverterModel model;

    public ConverterController() {
        this.model = new ConverterModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] units = {"Американские чашки (cup)", "Жидкие унции (fl oz)", "Миллилитры (ml)"};
        fromUnit.getItems().addAll(units);
        toUnit.getItems().addAll(units);

        fromUnit.setValue(units[0]);
        toUnit.setValue(units[2]);

        inputField.textProperty().addListener((_, _, _) -> errorLabel.setText(""));
    }

    @FXML
    private void handleConvert() {
        if (fromUnit.getValue() == null || toUnit.getValue() == null) {
            errorLabel.setText("Пожалуйста, выберите единицы измерения");
            return;
        }

        String input = inputField.getText();

        if (InputValidator.isEmpty(input)) {
            errorLabel.setText("Ошибка: Введите значение");
            resultLabel.setText("Результат: ");
            return;
        }

        if (!InputValidator.isNumeric(input)) {
            errorLabel.setText("Ошибка: Введите корректное число");
            resultLabel.setText("Результат: ");
            return;
        }

        if (!InputValidator.isPositive(input)) {
            errorLabel.setText("Ошибка: Введите положительное число");
            resultLabel.setText("Результат: ");
            return;
        }

        try {
            double value = Double.parseDouble(input.trim());
            String from = fromUnit.getValue();
            String to = toUnit.getValue();

            double result = model.convert(value, from, to);

            String formattedResult = String.format("%.2f", result);
            resultLabel.setText("Результат: " + formattedResult + " " + getUnitShortName(to));
            errorLabel.setText("");

        } catch (Exception e) {
            errorLabel.setText("Ошибка конвертации: " + e.getMessage());
            resultLabel.setText("Результат: ");
        }
    }

    private String getUnitShortName(String unit) {
        return switch (unit) {
            case "Американские чашки (cup)" -> "cup";
            case "Жидкие унции (fl oz)" -> "fl oz";
            case "Миллилитры (ml)" -> "ml";
            default -> "";
        };
    }
}
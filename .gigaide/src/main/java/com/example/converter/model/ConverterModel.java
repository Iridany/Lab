package com.example.converter.model;

public class ConverterModel {

    private final double CUP_TO_ML = 240.0;
    private final double OZ_TO_ML = 29.5735;

    public double convert(double value, String from, String to) {
        if (from.equals(to)) {
            return value;
        }

        double valueInMl = toMl(value, from);

        return fromMl(valueInMl, to);
    }

    private double toMl(double value, String unit) {
        switch (unit) {
            case "Американские чашки (cup)":
                return value * CUP_TO_ML;
            case "Жидкие унции (fl oz)":
                return value * OZ_TO_ML;
            case "Миллилитры (ml)":
                return value;
            default:
                throw new IllegalArgumentException("Неизвестная единица: " + unit);
        }
    }

    private double fromMl(double ml, String unit) {
        switch (unit) {
            case "Американские чашки (cup)":
                return ml / CUP_TO_ML;
            case "Жидкие унции (fl oz)":
                return ml / OZ_TO_ML;
            case "Миллилитры (ml)":
                return ml;
            default:
                throw new IllegalArgumentException("Неизвестная единица: " + unit);
        }
    }
}
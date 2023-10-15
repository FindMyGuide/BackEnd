package com.find_my_guide.main_tour_product.want_tour_product.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class VehicleConverter implements AttributeConverter<Vehicle, String> {

    @Override
    public String convertToDatabaseColumn(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        return vehicle.getKorean();
    }

    @Override
    public Vehicle convertToEntityAttribute(String dbData) {
        System.out.println("DB Data: " + dbData); // 로깅
        if (dbData == null) {
            return null;
        }
        return Vehicle.fromString(dbData);
    }
}
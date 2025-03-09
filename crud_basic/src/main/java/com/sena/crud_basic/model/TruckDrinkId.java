package com.sena.crud_basic.model;

import java.io.Serializable;
import java.util.Objects;

public class TruckDrinkId implements Serializable {
    private Long truck;
    private Long drink;

    public TruckDrinkId() {}

    public TruckDrinkId(Long truck, Long drink) {
        this.truck = truck;
        this.drink = drink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckDrinkId that = (TruckDrinkId) o;
        return Objects.equals(truck, that.truck) && Objects.equals(drink, that.drink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truck, drink);
    }
}


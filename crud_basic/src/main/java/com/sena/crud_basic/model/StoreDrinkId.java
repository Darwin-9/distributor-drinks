package com.sena.crud_basic.model;

import java.io.Serializable;
import java.util.Objects;

public class StoreDrinkId implements Serializable {
    private Long store;
    private Long drink;

    // Constructor vacío
    public StoreDrinkId() {}

    // Constructor con parámetros
    public StoreDrinkId(Long store, Long drink) {
        this.store = store;
        this.drink = drink;
    }

    // Equals y HashCode (requeridos para claves compuestas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreDrinkId that = (StoreDrinkId) o;
        return Objects.equals(store, that.store) && Objects.equals(drink, that.drink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(store, drink);
    }
}

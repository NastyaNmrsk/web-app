package org.example.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Data
@XmlRootElement
public class Car {
    private int id;
    private String model;
    private double price;

    public Car(int id, String model, double price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }

    public Car(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public Car(int id) {
        this.id = id;
    }

    public Car() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.garguir.models;

import java.util.Objects;

public class Document {
    private String type;
    private String number;

    public Document() {
    }

    public Document(String type, String number) {
        this.type = type;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Document{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(type, document.type) && Objects.equals(number, document.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number);
    }
}

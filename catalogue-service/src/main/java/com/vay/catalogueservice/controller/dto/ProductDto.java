package com.vay.catalogueservice.controller;

import java.io.Serializable;

public record ProductDto(String name, String description, double price) implements Serializable {
}

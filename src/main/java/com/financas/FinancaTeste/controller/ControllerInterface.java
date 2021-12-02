package com.financas.FinancaTeste.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerInterface<T> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<?> get(Long id);
    ResponseEntity<T> post(T obj);
    ResponseEntity<?> delete(Long id);
}

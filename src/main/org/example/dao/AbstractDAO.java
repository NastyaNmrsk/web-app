package org.example.dao;

import java.util.Set;

public abstract class AbstractDAO <T>{
    public abstract boolean insert(T t);
    public abstract boolean update(T t);
    public abstract boolean delete(T t);
    public abstract T getById(T t);
    public abstract Set<T> getAll();

}

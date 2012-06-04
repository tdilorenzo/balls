package com.tdilo.ballgame.service;

import java.util.List;


public interface DaoService<E,K> {
    E get(K id);
    List<E> getAll();
    void save(E entity);
    void merge(E entity);
    void refresh(E entity);
    void delete(E entity);
}

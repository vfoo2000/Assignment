package com.vn.DAO;

import java.util.List;

public interface GenericDAO<ET, Type> {
    ET create(ET entity);

    List<ET> readAll();

    ET readById(Type id);

    ET update(ET entity);

    ET deleteById(Type id);
}

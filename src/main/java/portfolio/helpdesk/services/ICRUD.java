package portfolio.helpdesk.services;

import java.util.List;

public interface ICRUD<T, ID> {
    T save(T t);
    List<T> findAll();

    T update(T t);
    T findById(ID id);
}

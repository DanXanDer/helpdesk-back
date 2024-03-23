package portfolio.helpdesk.services;

public interface ICRUD<T, ID> {
    T save(T t);
    T findById(ID id);
    T getReferenceById(ID id);
}

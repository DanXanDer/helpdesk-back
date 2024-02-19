package portfolio.helpdesk.services.implementation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.repositories.IGenericRepo;
import portfolio.helpdesk.services.ICRUD;

import java.util.List;

public abstract class CrudImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(ID id, T t) {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException(id));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException(id));
    }

    @Override
    public void delete(ID id) {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException(id));
        getRepo().deleteById(id);
    }
}

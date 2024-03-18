package portfolio.helpdesk.services.implementation;

import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.repositories.IGenericRepo;
import portfolio.helpdesk.services.ICRUD;

public abstract class CrudImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();
    @Override
    public T save(T t) {
        return getRepo().save(t);
    }
    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("Modelo no encontrado"));
    }

    @Override
    public T getReferenceById(ID id) {
        return getRepo().getReferenceById(id);
    }

}

package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.repositories.IGenericRepo;
import portfolio.helpdesk.services.ICRUD;

import java.util.List;

@RequiredArgsConstructor
public class CrudImpl<T, ID> implements ICRUD<T, ID> {

    private final IGenericRepo<T, ID> genericRepo;

    @Override
    public T save(T t) {
        return genericRepo.save(t);
    }

    @Override
    public T update(ID id, T t) {
        genericRepo.findById(id).orElseThrow(() -> new ModelNotFoundException(id));
        return genericRepo.save(t);
    }

    @Override
    public List<T> findAll() {
        return genericRepo.findAll();
    }

    @Override
    public T findById(ID id) {
        return genericRepo.findById(id).orElseThrow(() -> new ModelNotFoundException(id));
    }

    @Override
    public void delete(ID id) {
        genericRepo.findById(id).orElseThrow(() -> new ModelNotFoundException(id));
        genericRepo.deleteById(id);
    }
}

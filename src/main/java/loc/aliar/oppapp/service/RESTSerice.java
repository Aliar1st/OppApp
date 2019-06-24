package loc.aliar.oppapp.service;

public interface RESTSerice<T> {
    T create(T model);

    T update(Long id, T model);

    T delete(Long id);
}

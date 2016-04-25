package repository;

import java.util.Set;

/**
 * Created by lodz on 2016/04/24.
 */
public interface Repository<E,ID> {
    E findByID(ID id);
    E save(E entity);
    E update(E entity);
    E delete(E entity);
    Set<E> findAll();
    int deleteAll();
}

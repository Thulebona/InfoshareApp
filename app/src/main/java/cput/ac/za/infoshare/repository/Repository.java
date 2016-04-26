package cput.ac.za.infoshare.repository;

import java.text.ParseException;
import java.util.Set;

/**
 * Created by user9 on 2016/04/21.
 */
public interface Repository<E,ID> {

    E findById(ID id) throws ParseException;

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll() throws ParseException;

    int deleteAll();
}

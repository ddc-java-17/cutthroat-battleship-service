package edu.cnm.deepdive.jata.model.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * Defines basic read only subset of CRUD operations.
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {

  /**
   * Returns all instances of the type.
   */
  List<T> findAll();

  /**
   * Returns all entities sorted by the given options
   * @param sort the Sort specification to sort the results by, can be Sort.unsorted(), must not be null.
   * @return all entities sorted by the given options
   */
  List<T> findAll(Sort sort);

  /**
   * Returns a Page of entities meeting the paging restriction provided in the Pageable object.
   * @param pageable   the pageable to request a paged result, can be Pageable.unpaged(), must not be null.
   * @return a page of entities
   */
  Page<T> findAll(Pageable pageable);

  /**
   * Retrieves an entity by its id.
   * @param id  must not be null.
   * @return id
   */
  Optional<T> findById(ID id);

  /**
   * Returns the number of entities available.
   */
  long count();
}

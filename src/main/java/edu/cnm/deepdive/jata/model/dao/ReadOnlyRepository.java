package edu.cnm.deepdive.jata.model.dao;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface ReadOnlyRepository<T> extends Repository<T> {

}

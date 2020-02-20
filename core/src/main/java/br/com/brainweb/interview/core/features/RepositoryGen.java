package br.com.brainweb.interview.core.features;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryGen<T> {


    int count();

    int saveAndUpdate(T h);

    int deleteById(UUID id);

    List<T> findAll();

    List<T> findByName(String name);

    Optional<T> findById(UUID id);


}

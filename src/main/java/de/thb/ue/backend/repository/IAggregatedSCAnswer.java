package de.thb.ue.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import de.thb.ue.backend.model.AggregatedSCAnswer;

@RepositoryDefinition(domainClass = AggregatedSCAnswer.class, idClass = Integer.class)
@Transactional(readOnly = true)
public interface IAggregatedSCAnswer extends CrudRepository<AggregatedSCAnswer, Serializable> {

    List<AggregatedSCAnswer> findAll();

}

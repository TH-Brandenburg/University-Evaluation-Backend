package de.thb.ue.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import de.thb.ue.backend.model.AggregatedMCAnswer;

@RepositoryDefinition(domainClass = AggregatedMCAnswer.class, idClass = Integer.class)
@Transactional(readOnly = true)
public interface IAggregatedMCAnswer extends CrudRepository<AggregatedMCAnswer, Serializable> {

    List<AggregatedMCAnswer> findAll();

}

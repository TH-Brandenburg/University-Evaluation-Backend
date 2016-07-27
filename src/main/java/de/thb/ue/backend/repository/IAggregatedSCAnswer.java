package de.thb.ue.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import de.thb.ue.backend.model.AggregatedSingleChoiceAnswer;

@RepositoryDefinition(domainClass = AggregatedSingleChoiceAnswer.class, idClass = Integer.class)
@Transactional(readOnly = true)
public interface IAggregatedSCAnswer extends CrudRepository<AggregatedSingleChoiceAnswer, Serializable> {

    List<AggregatedSingleChoiceAnswer> findAll();

}

package de.thb.ue.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import de.thb.ue.backend.model.Answer;
import de.thb.ue.backend.model.Choice;

@RepositoryDefinition(domainClass = Answer.class, idClass = Integer.class)
@Transactional(readOnly = true)
public interface IAnswer extends CrudRepository<Answer, Serializable> {

    List<Answer> findAll();

    @Query("SELECT a FROM Answer a WHERE a.text = :text")
    Choice findByText(@Param("text") String text);

//    @Query("SELECT w FROM Word w WHERE lower(w.word) LIKE CONCAT('%', LOWER(:search), '%') ORDER BY w.id DESC")
//    public List<Word> findBySearchString(@Param("search") String search);

}

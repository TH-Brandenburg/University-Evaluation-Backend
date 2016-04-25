package de.thb.ue.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import de.thb.ue.backend.model.Choice;

@RepositoryDefinition(domainClass = Choice.class, idClass = Integer.class)
@Transactional(readOnly = true)
public interface IChoice extends CrudRepository<Choice, Serializable> {


    List<Choice> findAll();

    @Query("SELECT c FROM Choice c WHERE c.text = :text and c.grade = :grade")
    List<Choice> findByTextGrade(@Param("text") String text, @Param("grade") short grade);

//    @Query("SELECT w FROM Word w WHERE lower(w.word) LIKE CONCAT('%', LOWER(:search), '%') ORDER BY w.id DESC")
//    public List<Word> findBySearchString(@Param("search") String search);

}

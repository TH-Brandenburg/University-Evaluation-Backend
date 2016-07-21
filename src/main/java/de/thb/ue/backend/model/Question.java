package de.thb.ue.backend.model;

import de.thb.ue.backend.util.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Toni Anders on 13.07.2016.
 */
@Getter
@Setter
@Entity
@Table(name = "QUESTION")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question extends BaseModel {

    public Question(){}
    public Question(QuestionType type, String text) {
        this.text = text;
        this.type = type;
    }

    @Column(table = "QUESTION")
    @NotNull
    private QuestionType type;

    @NotNull
    private String text;


    private boolean adhocQuestion;


    private int questionPosition;

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Question)) {
            if (this.getId() == ((Question) obj).getId()) {
                return true;
            }
        }
        return false;
    }
}

package de.thb.ue.backend.util.eval_helper;

import de.thb.ue.backend.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartz, Tobias @Tobi-PC on 13.01.2017 at 18:02.
 */
public class EvalScQuestion extends EvalQuestion {

    private List<Short> grades = new ArrayList<>();
    private Float mw;

    public EvalScQuestion() {}

    public EvalScQuestion(List<Short> grades, Float mw) {
        this.grades = grades;
        this.mw = mw;
    }

    public List<Short> getGrades() {
        return grades;
    }

    public Float getMw() {
        return mw;
    }

    public void setGrades(List<Short> grades) {
        this.grades = grades;
    }

    public void setMw(Float mw) {
        this.mw = mw;
    }
}

package de.thb.ue.backend.util.eval_helper;

import de.thb.ue.backend.model.Evaluation;
import de.thb.ue.backend.model.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bartz, Tobias @Tobi-PC on 13.01.2017 at 18:08.
 */
public class EvalHelper {

    private final Evaluation evaluation;

    private final HashMap<Question, EvalScQuestion> questions;

    public EvalHelper(Evaluation evaluation, HashMap<Question, EvalScQuestion> questions) {
        this.evaluation = evaluation;
        this.questions = questions;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public HashMap<Question, EvalScQuestion> getQuestions() {
        return questions;
    }
}

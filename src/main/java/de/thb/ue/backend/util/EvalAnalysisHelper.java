package de.thb.ue.backend.util;

import de.thb.ue.backend.model.Evaluation;
import de.thb.ue.backend.model.Question;

import java.util.Map;

/**
 * Created by Bartz, Tobias @Tobi-PC on 10.12.2016 at 15:59.
 */
public class EvalAnalysisHelper {

    private final Evaluation evaluation;

    private final Map<Question, AggregateEvaluationHelper> rating;

    public EvalAnalysisHelper(Evaluation evaluation, Map<Question, AggregateEvaluationHelper> rating) {
        this.evaluation = evaluation;
        this.rating = rating;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public Map<Question, AggregateEvaluationHelper> getRating() {
        return rating;
    }
}

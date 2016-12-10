package de.thb.ue.backend.service.interfaces;

import de.thb.ue.backend.model.SingleChoiceAnswer;
import de.thb.ue.backend.util.AggregateEvaluationHelper;

import java.util.List;

/**
 * Created by Bartz, Tobias @Tobi-PC on 10.12.2016 at 22:24.
 */
public interface ISCAnswerService {

    AggregateEvaluationHelper computeAvgGrade(List<SingleChoiceAnswer> singleChoiceAnswers);
}

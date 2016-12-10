package de.thb.ue.backend.service;

import de.thb.ue.backend.model.SingleChoiceAnswer;
import de.thb.ue.backend.repository.ISCAnswer;
import de.thb.ue.backend.service.interfaces.ISCAnswerService;
import de.thb.ue.backend.util.AggregateEvaluationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bartz, Tobias @Tobi-PC on 10.12.2016 at 22:25.
 */
@Component
@Service
public class SCAnswerService implements ISCAnswerService {

    @Autowired
    private ISCAnswer answerRepo;

    @Override
    public AggregateEvaluationHelper computeAvgGrade(List<SingleChoiceAnswer> singleChoiceAnswers) {
        return answerRepo.getAvgGrade(singleChoiceAnswers);
    }
}

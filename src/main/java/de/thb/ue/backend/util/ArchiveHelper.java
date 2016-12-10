package de.thb.ue.backend.util;

import de.thb.ue.backend.model.Evaluation;
import de.thb.ue.backend.model.Question;
import de.thb.ue.backend.model.SingleChoiceAnswer;
import de.thb.ue.backend.model.Vote;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bartz, Tobias @Tobi-PC on 20.11.2016 at 19:40.
 */
public class ArchiveHelper {

    public Map<Question, Float> computeAverageGrade(Evaluation evaluation) {
        Map<Question, Float> result = new HashMap<>();

        for (Vote vote : evaluation.getVotes()) {
            for (SingleChoiceAnswer scAnswer : vote.getSingleChoiceAnswers()) {
                if (result.containsKey(scAnswer.getQuestion())) {
                    Float temp = result.get(scAnswer.getQuestion());
                    temp += scAnswer.getChoice().getGrade();
                    result.replace(scAnswer.getQuestion(), temp);
                } else {
                    result.put(scAnswer.getQuestion(), (float) scAnswer.getChoice().getGrade());
                }
            }

            for (Map.Entry<Question, Float> entry : result.entrySet()) {
                Float value = entry.getValue();
                value = value / evaluation.getVotes().size();
                entry.setValue(value);
            }
        }
        return result;
    }

}

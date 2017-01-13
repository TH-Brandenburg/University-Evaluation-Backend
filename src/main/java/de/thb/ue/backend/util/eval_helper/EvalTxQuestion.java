package de.thb.ue.backend.util.eval_helper;

import de.thb.ue.backend.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartz, Tobias @Tobi-PC on 13.01.2017 at 18:03.
 */
public class EvalTxQuestion extends EvalQuestion {

    private List<String> texts = new ArrayList<>();

    public EvalTxQuestion() {    }

    public EvalTxQuestion(List<String> texts) {
        this.texts = texts;
    }

    public List<String> getTexts() {
        return texts;
    }
}

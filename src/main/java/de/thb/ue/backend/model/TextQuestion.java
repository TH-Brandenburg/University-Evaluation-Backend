/*
 * Copyright 2016 Max Gregor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.thb.ue.backend.model;


import de.thb.ue.backend.util.QuestionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "text_question")
@PrimaryKeyJoinColumn(name = "QUESTION_ID")
public class TextQuestion extends Question {


    @NotNull
    private Boolean onlyNumbers;
    @NotNull
    private Integer maxLength;

    public TextQuestion(QuestionType type, String text,boolean onlyNumbers, int maxLength) {
        super(type, text);
        this.onlyNumbers = onlyNumbers;
        this.maxLength = maxLength;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

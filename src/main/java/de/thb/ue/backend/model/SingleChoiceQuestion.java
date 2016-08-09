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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


import de.thb.ue.backend.util.QuestionType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "single_choice_question")
@PrimaryKeyJoinColumn(name = "QUESTION_ID")
public class SingleChoiceQuestion extends Question {

	public SingleChoiceQuestion() {
		super();
	}

	public SingleChoiceQuestion(QuestionType type, String text, List<Choice> choices) {
		super(type, text);
		this.choices = choices;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Choice> choices;

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

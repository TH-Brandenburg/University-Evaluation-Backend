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

import de.thb.ue.backend.util.SemesterType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "evaluation")
public class Evaluation extends BaseModel {

    @NotNull
    @Column(unique = true)
    private String uid;

    @NotNull
    private LocalDateTime dateOfEvaluation;

    private Integer semester;

    @NotNull
    @ManyToMany
    @JoinTable(name = "evaluation_tutor", joinColumns = {@JoinColumn(name = "evaluation_id")} , inverseJoinColumns = {@JoinColumn(name = "tutor_id")})
    private List<Tutor> tutors;

    @NotNull
    @ManyToOne
    private Subject subject;

    @NotNull
    private SemesterType semesterType;

    private Boolean closed;

    @NotNull
    @ManyToOne
    private QuestionRevision questionRevision;

    @OneToMany
    private List<Vote> votes;

    @NotNull
    private Integer studentsAll;

    @NotNull
    private Integer studentsVoted;

    @OneToMany
    private List<Question> adhocQuestions;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

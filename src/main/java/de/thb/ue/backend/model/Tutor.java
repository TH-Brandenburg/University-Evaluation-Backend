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

import de.thb.ue.dto.util.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "tutor")
public class Tutor extends BaseModel {

    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String familyName;

    @NotNull
    private Department department;

    private Boolean isSuperuser;

    @ManyToMany
    @JoinTable(name = "evaluation_tutor", joinColumns = {@JoinColumn(name = "tutor_id")}, inverseJoinColumns = {@JoinColumn(name = "evaluation_id")})
    private List<Evaluation> evaluations;


    public Tutor(String username, String name, String familyName, Department department, List<Evaluation> evaluations) {
        this.username = username;
        this.name = name;
        this.familyName = familyName;
        this.department = department;
        this.evaluations = evaluations;
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

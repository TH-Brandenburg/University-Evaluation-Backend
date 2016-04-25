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

package de.thb.ue.backend.service.interfaces;

import java.util.List;

import de.fhb.ca.dto.util.Department;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.model.StudyPath;
import de.thb.ue.backend.util.Degree;

public interface IStudyPathService {
    List<String> getByDegreeAndSection(Degree degree, Department department);

    List<StudyPath> getStudyPathByEvaluationUID(String evaluationUID) throws EvaluationException, DBEntryDoesNotExistException;

    List<String> getByEvaluationUID(String evaluationUID) throws EvaluationException, DBEntryDoesNotExistException;

    @Deprecated
    List<String> getAll();

}

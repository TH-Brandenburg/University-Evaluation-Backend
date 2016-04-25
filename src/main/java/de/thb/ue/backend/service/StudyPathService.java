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

package de.thb.ue.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import de.thb.ue.dto.util.Department;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.model.StudyPath;
import de.thb.ue.backend.model.Subject;
import de.thb.ue.backend.repository.IStudyPath;
import de.thb.ue.backend.service.interfaces.IStudyPathService;
import de.thb.ue.backend.util.DTOMapper;
import de.thb.ue.backend.util.Degree;

@Component
@Service
public class StudyPathService implements IStudyPathService {

    @Autowired
    private IStudyPath innerSectionRepo;

    @Autowired
    private EvaluationService evaluationService;


    @Override
    public List<String> getAll() {
        return innerSectionRepo.findAll().stream().map(StudyPath::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getByDegreeAndSection(Degree degree, Department department) {
        return DTOMapper.innerSectionsToStrings(innerSectionRepo.findByDegreeAndSection(degree, department));
    }

    @Override
    public List<String> getByEvaluationUID(String evaluationUID) throws EvaluationException, DBEntryDoesNotExistException {
        Subject subject = evaluationService.getByUID(evaluationUID).getSubject();
        return DTOMapper.innerSectionsToStrings(innerSectionRepo.findByDegreeAndSection(subject.getDegree(), subject.getDepartment()));
    }

    @Override
    public List<StudyPath> getStudyPathByEvaluationUID(String evaluationUID) throws EvaluationException, DBEntryDoesNotExistException {
        Subject subject = evaluationService.getByUID(evaluationUID).getSubject();
        return innerSectionRepo.findByDegreeAndSection(subject.getDegree(), subject.getDepartment());
    }
}

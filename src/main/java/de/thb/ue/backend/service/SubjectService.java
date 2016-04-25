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

import de.thb.ue.backend.model.Subject;
import de.thb.ue.backend.repository.ISubject;
import de.thb.ue.backend.service.interfaces.ISubjectService;

@Component
@Service
public class SubjectService implements ISubjectService {

    @Autowired
    private ISubject subjectRepo;


    @Override
    public List<Subject> getByName(String text) {
        return subjectRepo.findByName(text);
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepo.findAllOrderByNameAsc();
    }

    @Override
    public Subject getByID(int id) {
        return subjectRepo.findOne(id);
    }
}

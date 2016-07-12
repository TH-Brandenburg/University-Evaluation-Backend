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

import de.thb.ue.backend.model.Tutor;
import de.thb.ue.backend.repository.ITutor;
import de.thb.ue.backend.service.interfaces.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class TutorService implements ITutorService {

    @Autowired
    private ITutor tutorRepo;


    @Override
    public List<Tutor> getByUsername(String username) {
        return tutorRepo.findByUsername(username);
    }

    @Override
    public List<Tutor> getByFamilyName(String name) {
        return tutorRepo.findByFamilyName(name);
    }

    @Override
    public List<Tutor> getAll() {
        return tutorRepo.findAllOrderByFamilyNameAsc();
    }
}

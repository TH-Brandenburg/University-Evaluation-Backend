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

package de.thb.ue.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import de.thb.ue.backend.model.Tutor;

@RepositoryDefinition(domainClass = Tutor.class, idClass = Integer.class)
@Transactional(readOnly = true)
public interface ITutor extends CrudRepository<Tutor, Serializable> {

    List<Tutor> findAll();

    @Query("SELECT t FROM Tutor t ORDER BY t.familyName ASC")
    List<Tutor> findAllOrderByFamilyNameAsc();

    @Query("SELECT t FROM Tutor t WHERE LOWER(t.familyName) = LOWER(:familyName)")
    List<Tutor> findByFamilyName(@Param("familyName") String familyName);

}

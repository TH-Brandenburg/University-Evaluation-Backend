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
import de.thb.ue.dto.util.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.NotFilter;
import org.springframework.ldap.filter.OrFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class TutorService implements ITutorService {

    @Autowired
    private ITutor tutorRepo;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public Tutor getByUsername(String username) {
        Tutor tutor = tutorRepo.findByUsername(username);
        if (tutor == null) {
            tutor = getLdapUser(username);
            tutorRepo.save(tutor);
        }
        return tutor;
    }

    @Override
    public List<Tutor> getByFamilyName(String name) {
        return tutorRepo.findByFamilyName(name);
    }

    @Override
    public List<Tutor> getAll() {
        return tutorRepo.findAllOrderByFamilyNameAsc();
    }

    private Tutor getLdapUser(String uid) {

        //Filter: (&(objectClass=inetOrgPerson)(fh-AGBBestaetigt=TRUE)(|(fh-MaStudiengang=*-L*)(fh-MaStudiengang=*-Y*))(!(loginDisabled=TRUE)))
        AndFilter filter = new AndFilter();
        OrFilter orFilter = new OrFilter();
        orFilter.or(new EqualsFilter("fh-MaStudiengang", "*-L*"));
        orFilter.or(new EqualsFilter("fh-MaStudiengang", "*-Y*"));
        filter.and(new EqualsFilter("objectclass", "inetOrgPerson"));
        filter.and(new EqualsFilter("fh-AGBBestaetigt", "TRUE"));
        filter.and(new NotFilter(new EqualsFilter("loginDisabled", "TRUE")));
        filter.and(orFilter);
        filter.and(new EqualsFilter("uid", uid));

        return (Tutor) ldapTemplate.search(
                "", filter.encode(), new PersonAttributesMapper()).get(0);
    }

    private class PersonAttributesMapper implements AttributesMapper {
        public Object mapFromAttributes(Attributes attrs) throws NamingException {
            Tutor tutor = new Tutor();
            tutor.setFamilyName((String) attrs.get("sn").get());
            tutor.setName((String) attrs.get("givenName").get());
            tutor.setUsername((String) attrs.get("uid").get());
            tutor.setEvaluations(new ArrayList<>());
            tutor.setDepartment(Department.COMPUTER_SCIENCE_MEDIA);
            return tutor;
        }
    }
}

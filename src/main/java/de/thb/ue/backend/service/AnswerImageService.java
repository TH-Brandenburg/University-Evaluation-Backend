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

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.thb.ue.backend.model.Vote;
import de.thb.ue.backend.service.interfaces.IAnswerImageService;
import de.thb.ue.backend.service.interfaces.IParticipantService;

@Component
@Service
public class AnswerImageService implements IAnswerImageService {

    @Autowired
    IParticipantService participantService;

    @Value("${working-directory:}")
    private String workingDirectoryPath;

    @Override
    public void addAnswerImage(Vote vote, MultipartFile answerImage, String evaluationID) {
        File imageFolder = new File((workingDirectoryPath.isEmpty() ? "" : (workingDirectoryPath + File.separatorChar)) + evaluationID + File.separatorChar + "images");
        try {
            if (!imageFolder.exists()) {
                FileUtils.forceMkdir(imageFolder);
            }
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(imageFolder, vote.getId() + ".zip")));
            stream.write(answerImage.getBytes());
            stream.close();
        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }
    }
}

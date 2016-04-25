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

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.apache.commons.io.FileUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.thb.ue.dto.util.ErrorType;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.exception.ParticipantException;
import de.thb.ue.backend.model.Evaluation;
import de.thb.ue.backend.model.Participant;
import de.thb.ue.backend.repository.IParticipant;
import de.thb.ue.backend.service.interfaces.IParticipantService;
import de.thb.ue.backend.util.PDFGeneration;
import de.thb.ue.backend.util.QRCGeneration;
import lombok.extern.slf4j.Slf4j;

@Component
@Service
@Slf4j
public class ParticipantService implements IParticipantService {

    @Autowired
    private IParticipant participantRepo;

    @Value("${working-directory:}")
    private String workingDirectoryPath;

    @Value("${hostaddress}")
    private String hostadress;

    @Override
    public List<Participant> add(int amount, Evaluation evaluation) throws EvaluationException, ParticipantException {
        List<Participant> createdParticipants = new ArrayList<>(amount);
        List<BufferedImage> qrcs = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            String voteToken = UUID.randomUUID().toString();
            createdParticipants.add(new Participant(evaluation, false, voteToken, ""));
            try {
                qrcs.add(QRCGeneration.generateQRC(
                        "{\"voteToken\":\"" + voteToken + "\",\"host\":\"" + hostadress + "\"}",
                        QRCGeneration.SIZE_SMALL, ErrorCorrectionLevel.Q, QRCGeneration.ENCODING_UTF_8));
            } catch (WriterException | IOException e) {
                throw new ParticipantException(ParticipantException.ERROR_CREATING_QRC_PDF, e.getMessage());
            }
        }
        participantRepo.save(createdParticipants);
        File workingDirectory = new File((workingDirectoryPath.isEmpty() ? "" : (workingDirectoryPath + File.separatorChar)) + evaluation.getUid());

        if (!workingDirectory.exists()) {
            try {
                FileUtils.forceMkdir(workingDirectory);
            } catch (IOException e) {
                log.error("Can't create directory for " + evaluation.getUid());
            }
        }

        PDFGeneration.createQRCPDF(qrcs, evaluation.getUid(),
                evaluation.getSubject().getName(), evaluation.getSemesterType(), LocalDate.now().getYear(), workingDirectory);
        return createdParticipants;
    }

    @Override
    public void setDeviceID(String voteToken, String deviceID) throws ParticipantException, DBEntryDoesNotExistException {
        Participant participant = participantRepo.findByVoteToken(voteToken);
        String deviceIDDB;
        if (participant != null) {
            deviceIDDB = participant.getDeviceID();
        } else {
            throw new DBEntryDoesNotExistException("No participant with given vote token");
        }
        if (deviceIDDB == null || deviceIDDB.isEmpty()) {
            participant.setDeviceID(deviceID);
            participantRepo.save(participant);
        } else if (!deviceIDDB.equals(deviceID)) {
            //TODO Static var
            throw new ParticipantException(6, "DeviceID already set");
        }
    }

    @Override
    public void setVoted(String voteToken, String deviceID) throws ParticipantException, DBEntryDoesNotExistException {
        Participant participant = participantRepo.findByVoteToken(voteToken);
        if (participant != null) {
            if (!participant.getDeviceID().equals(deviceID)) {
                throw new ParticipantException(ErrorType.WRONG_DEVICE_ID, "The given deviceID don't belong to the voteToken");
            } else if (participant.isVoted()) {
                throw new ParticipantException(ParticipantException.ALREADY_VOTED);
            } else {
                participant.setVoted(true);
                participant.setDeviceID("");
                participantRepo.save(participant);
            }
        } else {
            throw new DBEntryDoesNotExistException("There is no participant with given voteToken");
        }
    }

    @Override
    public Evaluation getEvaluation(String voteToken) throws DBEntryDoesNotExistException {
        Participant participant = participantRepo.findByVoteToken(voteToken);
        Evaluation out;
        if (participant != null) {
            out = participant.getEvaluation();
        } else {
            throw new DBEntryDoesNotExistException("There is no participant with token: " + voteToken);
        }
        return out;
    }

    @Override
    public List<Participant> getByEvaluation(Evaluation evaluation) {
        return participantRepo.findByEvaluation(evaluation);
    }

    @Override
    public boolean hasVoted(String voteToken) {
        return participantRepo.findByVoteToken(voteToken).isVoted();
    }

    @Override
    public void deleteByEvaluation(Evaluation evaluation) {
        participantRepo.deleteByEvaluation(evaluation);
    }

}

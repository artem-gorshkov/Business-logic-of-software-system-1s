package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.firstlab.data.entity.Status;
import ru.itmo.blss.firstlab.data.repository.StatusRepository;

@Service
@AllArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;

    public Status getSubmittedStatus() {
        return statusRepository.getStatusByName("Submitted");
    }
}

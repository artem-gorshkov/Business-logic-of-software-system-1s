package ru.itmo.blss.main.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.main.data.entity.Status;
import ru.itmo.blss.main.data.repository.StatusRepository;

@Service
@AllArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;

    public Status getSubmittedStatus() {
        return statusRepository.getStatusByName("Submitted");
    }

    public Status getRejectedStatus() {
        return statusRepository.getStatusByName("Rejected");
    }

    public Status getAcceptedStatus() {
        return statusRepository.getStatusByName("Accepted");
    }
}

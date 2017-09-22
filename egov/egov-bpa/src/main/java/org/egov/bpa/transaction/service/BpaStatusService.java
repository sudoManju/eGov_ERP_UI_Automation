package org.egov.bpa.transaction.service;

import java.util.List;

import org.egov.bpa.transaction.entity.BpaStatus;
import org.egov.bpa.transaction.repository.BpaStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BpaStatusService {

    private final BpaStatusRepository bpaStatusRepository;

    @Autowired
    public BpaStatusService(final BpaStatusRepository bpaStatusRepository) {
        this.bpaStatusRepository = bpaStatusRepository;
    }

    public BpaStatus findByModuleTypeAndCode(final String moduleType, final String code) {
        return bpaStatusRepository.findByModuleTypeContainingIgnoreCaseAndCode(moduleType, code);
    }

    public List<BpaStatus> findAll() {
        return bpaStatusRepository.findAll();
    }
}

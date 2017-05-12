package org.egov.bpa.masters.repository;

import org.egov.bpa.application.entity.ConstructionStages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionStagesRepository  extends JpaRepository<ConstructionStages, Long> {

}

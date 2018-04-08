package com.gitlab.esenbogagnu.urmsweb.repository;

import com.gitlab.esenbogagnu.urmsweb.domain.Risk;
import com.gitlab.esenbogagnu.urmsweb.domain.RiskLikelihood;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Repository
public interface RiskRepository extends CrudRepository<Risk, Long> {

	List<Risk> findBySubject(String subject);

	List<Risk> findByIsResolved(boolean isResolved);

	List<Risk> findByLikelihood(RiskLikelihood likelihood);
}

package com.gitlab.esenbogagnu.urmsweb.repository;

import com.gitlab.esenbogagnu.urmsweb.domain.Risk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
public interface RiskRepository extends CrudRepository<Risk, Long> {

	List<Risk> findBySubject(String subject);
}

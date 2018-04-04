package com.gitlab.esenbogagnu.urmsweb.repository;

import com.gitlab.esenbogagnu.urmsweb.domain.Risk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Repository
public interface RiskRepository extends CrudRepository<Risk, Long> {

}

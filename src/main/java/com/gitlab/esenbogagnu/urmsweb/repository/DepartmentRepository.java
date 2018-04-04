package com.gitlab.esenbogagnu.urmsweb.repository;

import com.gitlab.esenbogagnu.urmsweb.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

}

package com.company.ProcessApplication.Repository;

import com.company.ProcessApplication.Model.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationDAO extends CrudRepository<Specification, Long> {
    public List<Specification> findByCost(long cost);

    public List<Specification> findBySpeed(long speed);
}

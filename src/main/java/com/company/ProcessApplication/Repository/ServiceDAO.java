package com.company.ProcessApplication.Repository;

import com.company.ProcessApplication.Model.Service;
import com.company.ProcessApplication.Model.ServiceStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ServiceDAO extends CrudRepository<Service, Long> {

    public List<Service> findByStatus(ServiceStatus status);

    public List<Service> findByStartDate(Date date);

    public List<Service> findByStartDateGreaterThan(Date date);

    public List<Service> findByStartDateLessThan(Date date);

    public List<Service> findByStopDate(Date date);

    public List<Service> findByStopDateLessThan(Date date);

    public List<Service> findByStopDateGreaterThan(Date date);
}

package com.company.ProcessApplication.Repository;

import com.company.ProcessApplication.Model.Order;
import com.company.ProcessApplication.Model.OrderStatus;
import com.company.ProcessApplication.Model.OrderType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderDAO extends CrudRepository<Order, Long> {
    public List<Order> findByType(OrderType type);

    public List<Order> findByStatus(OrderStatus status);

    public List<Order> findByScheduledDateLessThan(Date date);

    public List<Order> findByScheduledDateGreaterThan(Date date);
}

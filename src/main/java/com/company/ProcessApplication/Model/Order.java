package com.company.ProcessApplication.Model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="ORDERS")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ORDER_ID", nullable = false)
    @Getter
    private Long id;

    @Column(name = "ORDER_TYPE", nullable = false)
    @Getter @Setter
    private OrderType type;

    @Column(name = "ORDER_STATUS", nullable = false)
    @Getter @Setter
    private OrderStatus status;

    @Column(name = "ORDER_SCHEDULED_DATE", nullable = false)
    @Getter @Setter
    private LocalDate scheduledDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "USER_NAME", nullable = false)
    private User user;

    @OneToOne(optional = false)
    @JoinColumn(name = "SPEC_ID", nullable = false)
    private Specification spec;

    //Constructor for standart Order creation by user
    public Order(Specification spec, User user, OrderType type) {
        this.spec = spec;
        this.user = user;
        this.type = type;
        this.scheduledDate = LocalDate.now().plusDays(3);
        this.status = OrderStatus.Entering;
    }

    //Constructor for preliminary Order creation & tests
    public Order(Specification spec, User user, OrderType type, OrderStatus status, LocalDate scheduledDate){
        this.spec = spec;
        this.user = user;
        this.type = type;
        this.scheduledDate = scheduledDate;
        this.status = status;
    }

}

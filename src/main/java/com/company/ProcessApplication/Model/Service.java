package com.company.ProcessApplication.Model;

import lombok.*;

import javax.persistence.*;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="SERVICES")
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="SERVICE_ID", nullable = false)
    @Getter
    private Long id;

    @Column(name="SERVICE_STATUS", nullable = false)
    @Getter @Setter
    private ServiceStatus status;

    @Column(name="SERVICE_START_DATE", nullable = false)
    @Getter @Setter
    private LocalDate startDate;

    @Column(name="SERVICE_STOP_DATE", nullable = true)
    @Getter @Setter
    private LocalDate stopDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "SPEC_ID", nullable = false)
    private Specification spec;

    public Service(Specification spec, ServiceStatus status, LocalDate startDate, LocalDate stopDate) {
        this.spec = spec;
        this.status = status;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }
}

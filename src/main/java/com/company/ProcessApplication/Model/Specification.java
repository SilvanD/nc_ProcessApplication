package com.company.ProcessApplication.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="SPECIFICATIONS")
@NoArgsConstructor
@AllArgsConstructor
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="SPEC_ID", nullable = false)
    @Getter
    private Long id;

    @Column(name="SPEC_SPEED", nullable = false)
    @Getter @Setter
    private Long speed;

    @Column(name="SPEC_COST", nullable = false)
    @Getter @Setter
    private Long cost;

    public Specification(Long speed, Long cost)  {
        this.speed = speed;
        this.cost = cost;
    }

    public Specification(long speed, long cost) {
        this.speed = new Long(speed);
        this.cost = new Long (cost);
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}

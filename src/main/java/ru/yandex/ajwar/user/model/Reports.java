package ru.yandex.ajwar.user.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Класс связи с таблицей
 */
@Entity
@Table(name = "Reports")
public class Reports {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    @Column(name="id")
    private Long id;

    @Column(name = "startDate" )
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "performer")
    private String performer;

    @Column(name = "activity")
    private  String activity;

    public Reports(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}

package com.sackmesser.tests.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.mapping.PrimaryKey;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Diogo
 * Date: 03/10/14
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */
@Setter
@Getter
@ToString
@Table(name = "player")
@Entity
public class Player implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "position")
    private String position;

    @Column(name = "birthYear")
    private int birthYear;

    @Column(name = "debutYear")
    private int debutYear;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @DateTimeFormat
    @Column(name = "anotherDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date anotherDate;

}

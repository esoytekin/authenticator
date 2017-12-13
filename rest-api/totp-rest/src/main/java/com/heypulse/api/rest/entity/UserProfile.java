package com.heypulse.api.rest.entity;

import com.heypulse.api.rest.model.BiologicalSex;
import com.heypulse.api.rest.model.BloodType;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by emrahsoytekin on 29.10.2017.
 */
@Entity
@Table(name = "H_USER_PROFILE")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private User user;

    @Column(name = "BLOOD_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private BloodType bloodType;

    @Column(name = "BIOLOGICAL_SEX")
    @Enumerated(EnumType.ORDINAL)
    private BiologicalSex biologicalSex;

    @Temporal (TemporalType.TIMESTAMP)
    @Column(name = "BIRTH_DATE", nullable =  false)
    private Date birthDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public BiologicalSex getBiologicalSex() {
        return biologicalSex;
    }

    public void setBiologicalSex(BiologicalSex biologicalSex) {
        this.biologicalSex = biologicalSex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}

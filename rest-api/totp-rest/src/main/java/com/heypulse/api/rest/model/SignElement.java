package com.heypulse.api.rest.model;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
public class SignElement {

    private String lgUsername;
    private String lgEmail;
    private String lgFirstName;
    private String lgLastName;
    private String lgPassword;
    private int bloodType;
    private int biologicalSex;
    private String birthDate;

    public String getLgUsername() {
        return lgUsername;
    }
    public void setLgUsername(String lgUsername) {
        this.lgUsername = lgUsername;
    }
    public String getLgEmail() {
        return lgEmail;
    }
    public void setLgEmail(String lgEmail) {
        this.lgEmail = lgEmail;
    }
    public String getLgFirstName() {
        return lgFirstName;
    }
    public void setLgFirstName(String lgFirstName) {
        this.lgFirstName = lgFirstName;
    }
    public String getLgLastName() {
        return lgLastName;
    }
    public void setLgLastName(String lgLastName) {
        this.lgLastName = lgLastName;
    }
    public String getLgPassword() {
        return lgPassword;
    }
    public void setLgPassword(String lgPassword) {
        this.lgPassword = lgPassword;
    }

    public int getBloodType() {
        return bloodType;
    }

    public void setBloodType(int bloodType) {
        this.bloodType = bloodType;
    }

    public int getBiologicalSex() {
        return biologicalSex;
    }

    public void setBiologicalSex(int biologicalSex) {
        this.biologicalSex = biologicalSex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "SignElement{" +
                "lgUsername='" + lgUsername + '\'' +
                ", lgEmail='" + lgEmail + '\'' +
                ", lgFirstName='" + lgFirstName + '\'' +
                ", lgLastName='" + lgLastName + '\'' +
                ", lgPassword='" + lgPassword + '\'' +
                ", bloodType=" + bloodType +
                ", biologicalSex=" + biologicalSex +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}

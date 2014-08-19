package com.richardlu.data;

/**
 * Created by Richard on 14-1-21.
 */
public class Patient {

    private Integer _id;
    private String name;
    private String sex;
    private String age;
    private String birthday;
    private String fundRemark;
    private String SN;
    private String inHospitalDate;
    private String diagnosis;
    private String surgeryDate;
    private String surgeon;
    private String specialWay;
    private String pathologicalDiagnosis;
    private String pathologicNumber;
    private String surgeryRemark;
    private String outDate;
    private String reviewPeriod;
    private String oralMedication;
    private String outRemark;
    private String isTreated;


    public Patient() {

    }

    //setter


    public void setId(Integer _id) {
        this._id = _id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setFundRemark(String fundRemark) {
        this.fundRemark = fundRemark;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public void setInHospitalDate(String inHospitalDate) {
        this.inHospitalDate = inHospitalDate;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setSurgeryRemark(String surgeryRemark) {
        this.surgeryRemark = surgeryRemark;
    }

    public void setSurgeryDate(String surgeryDate) {
        this.surgeryDate = surgeryDate;
    }

    public void setSurgeon(String surgeon) {
        this.surgeon = surgeon;
    }

    public void setSpecialWay(String specialWay) {
        this.specialWay = specialWay;
    }

    public void setPathologicalDiagnosis(String pathologicalDiagnosis) {
        this.pathologicalDiagnosis = pathologicalDiagnosis;
    }

    public void setPathologicNumber(String pathologicNumber) {
        this.pathologicNumber = pathologicNumber;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public void setReviewPeriod(String reviewPeriod) {
        this.reviewPeriod = reviewPeriod;
    }

    public void setOralMedication(String oralMedication) {
        this.oralMedication = oralMedication;
    }

    public void setOutRemark(String outRemark) {
        this.outRemark = outRemark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsTreated(String isTreated) {
        this.isTreated = isTreated;
    }


    //getter
    public Integer getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getage() {
        return age;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getFundRemark() {
        return fundRemark;
    }

    public String getSN() {
        return SN;
    }

    public String getInHospitalDate() {
        return inHospitalDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getSurgeryDate() {
        return surgeryDate;
    }

    public String getSurgeon() {
        return surgeon;
    }

    public String getSpecialWay() {
        return specialWay;
    }

    public String getPathologicalDiagnosis() {
        return pathologicalDiagnosis;
    }

    public String getPathologicNumber() {
        return pathologicNumber;
    }

    public String getSurgeryRemark() {
        return surgeryRemark;
    }

    public String getOutDate() {
        return outDate;
    }

    public String getReviewPeriod() {
        return reviewPeriod;
    }

    public String getOralMedication() {
        return oralMedication;
    }

    public String getOutRemark() {
        return outRemark;
    }

    public String getIsTreated() {
        return isTreated;
    }


}

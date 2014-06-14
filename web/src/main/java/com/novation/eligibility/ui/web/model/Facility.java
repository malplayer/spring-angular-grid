package com.novation.eligibility.ui.web.model;

/**
 * Created with IntelliJ IDEA.
 * User: ajmal
 */
public class Facility {
    private Long id;
    private String name;
    private Integer identification;
    private Boolean apexus;

    public Facility() { }

    public Facility(Long id, String name, Integer identification, Boolean apexus) {
        this.id = id;
        this.name = name;
        this.identification = identification;
        this.apexus = apexus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdentification() {
        return identification;
    }

    public void setIndetification(Integer identification) {
        this.identification = identification;
    }

    public Boolean getApexus() {
        return apexus;
    }

    public void setApexus(Boolean apexus) {
        this.apexus = apexus;
    }
}

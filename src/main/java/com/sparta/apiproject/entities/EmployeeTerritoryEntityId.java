package com.sparta.apiproject.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeTerritoryEntityId implements Serializable {
    private static final long serialVersionUID = -4919583674846843228L;
    @Column(name = "EmployeeID", nullable = false)
    private Integer employeeID;
    @Column(name = "TerritoryID", nullable = false, length = 20)
    private String territoryID;

    public String getTerritoryID() {
        return territoryID;
    }

    public void setTerritoryID(String territoryID) {
        this.territoryID = territoryID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(territoryID, employeeID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeeTerritoryEntityId entity = (EmployeeTerritoryEntityId) o;
        return Objects.equals(this.territoryID, entity.territoryID) &&
                Objects.equals(this.employeeID, entity.employeeID);
    }
}
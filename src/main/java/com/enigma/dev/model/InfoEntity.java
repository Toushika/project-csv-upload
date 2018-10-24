package com.enigma.dev.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Entity
@Table(name = "detail_info")
public class InfoEntity  implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "row_id")
    private Integer rowId;

    @Column(name = "name")
    @Size(max = 255)
    private String name;

    @Column(name = "email")
    @Size(max = 255)
    private String email;

    @Column(name = "phone")
    @Size(max = 255)
    private String phone;


    public InfoEntity() {
    }


    public InfoEntity(@Size(max = 255) String name, @Size(max = 255) String email, @Size(max = 255) String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "InfoEntity{" +
                "rowId=" + rowId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}


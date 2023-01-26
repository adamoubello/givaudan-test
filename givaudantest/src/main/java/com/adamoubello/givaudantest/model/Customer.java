package com.adamoubello.givaudantest.model;

import java.util.HashSet;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.text.Normalizer;
import java.util.*;

@Entity
@Indexed
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="idcustomer"
// , scope = Customer.class)
//@JsonIdentityReference(alwaysAsId=true)
public class Customer implements java.io.Serializable {

    private static final long serialVersionUID = 4910225916550731446L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcustomer", unique = true, nullable = false)
    private Long idcustomer;

    @Column(name = "firstName", length = 250)
    private String firstName;

    @Column(name = "lastName", length = 250)
    private String lastName;

    @Column(name = "role")
    private String role;

    @Column(name = "amount", length = 250)
    private String amount;

    @Column(name = "telephone", length = 250)
    private String telephone;

    @Column(name = "email", length = 250)
    private String email;

    public Customer() {
    }

    public Customer(Long idcustomer, String firstName, String lastName, String role, String amount, String telephone
            , String email) {
        this.idcustomer = idcustomer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.amount = amount;
        this.telephone = telephone;
        this.email = email;
    }

    public Long getIdcustomer() {
        return idcustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getAmount() {
        return amount;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setIdcustomer(Long idcustomer) {
        this.idcustomer = idcustomer;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

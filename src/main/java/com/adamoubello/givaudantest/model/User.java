package com.adamoubello.givaudantest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.util.Set;

@Entity
@Indexed
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "datenaissance", length = 255)
    private String datenaissance;

    @Column(name = "nom", length = 255)
    @Field(termVector = TermVector.YES)
    private String nom;


    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "prenom", length = 255)
    @Field(termVector = TermVector.YES)
    private String prenom;

    @Column(name = "profession", length = 255)
    @Field(termVector = TermVector.YES)
    private String profession;

    /*@Column(name = "username", length = 255)
    @Field(termVector = TermVector.YES)
    private String username;*/

    @Column(name = "email", length = 255)
    @Field(termVector = TermVector.YES)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", datenaissance='").append(datenaissance).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", profession='").append(profession).append('\'');
        //sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}

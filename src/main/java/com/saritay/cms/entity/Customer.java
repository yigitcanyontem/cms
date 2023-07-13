package com.saritay.cms.entity;

import com.saritay.cms.dto.Active;
import com.saritay.cms.dto.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_email_unique",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                    name = "user_telNo_unique",
                    columnNames = "telNo"
                ),
            }
        )
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Customer {
    @Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq"
    )
    @Column(name = "id")
    private Integer id;

    @Column(
            nullable = false
    )
    private String firstName;

    @Column(
            nullable = false
    )
    private String lastName;

    @Column(
            nullable = false
    )
    private String email;

    @Column(
            nullable = false
    )
    private String telNo;


    @Column(
            nullable = false
    )
    private String password;

    @Column(
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Active isActive;

    public Customer(String firstName, String lastName, String email, String telNo, String password, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telNo = telNo;
        this.password = password;
        this.gender = gender;
    }
    public Customer(String firstName, String lastName, String email, String telNo, String password, Gender gender, Active isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telNo = telNo;
        this.password = password;
        this.gender = gender;
        this.isActive = isActive;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Active getIsActive() {
        return isActive;
    }

    public void setIsActive(Active isActive) {
        this.isActive = isActive;
    }
}

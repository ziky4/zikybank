package com.ziky.bank.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.DateSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ziky on 1.10.2014.
 */
public class Person extends Id<String> implements Serializable {
    private String login;
    private String password;
    private Integer idCardNo;
    private String firstName;
    private String lastName;
    @JsonSerialize(using=DateSerializer.class)
    private Date dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private Role roleName;

    public Person() {
    }

    public Person(String login, String password, String firstName, String lastName, Integer idCardNo, Date dateOfBirth, String email, String phone, String address, Role roleName) {
        this.password = password;
        this.login = login;
        this.firstName = firstName;
        this.idCardNo = idCardNo;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roleName = roleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(Integer idCardNo) {
        this.idCardNo = idCardNo;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (address != null ? !address.equals(person.address) : person.address != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(person.dateOfBirth) : person.dateOfBirth != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (idCardNo != null ? !idCardNo.equals(person.idCardNo) : person.idCardNo != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (login != null ? !login.equals(person.login) : person.login != null) return false;
        if (password != null ? !password.equals(person.password) : person.password != null) return false;
        if (phone != null ? !phone.equals(person.phone) : person.phone != null) return false;
        if (roleName != null ? !roleName.equals(person.roleName) : person.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (idCardNo != null ? idCardNo.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @Override
    public String getId() {
        return login;
    }
}

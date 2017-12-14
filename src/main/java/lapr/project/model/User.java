/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author Utilizador
 */
public class User {
    private static final long serialVersionUID = 102L;
    
    private String username;
    private int charKey;
    private String name;
    private String password;
    private String email;

    public User(String username, int charKey, String nome, String password, String email) {
        this.username = username;
        this.charKey = charKey;
        this.name = nome;
        this.password = password;
        this.email = email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return the integer representation of the object User
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.username);
        hash = 61 * hash + Objects.hashCode(this.password);
        hash = 61 * hash + Objects.hashCode(this.email);
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the User
     * @return the result of the comparisons made. 
     * True if the objects are the same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (!this.username.equals(other.username)) {
            return false;
        }
        if (!this.email.equals(other.email)) {
            return false;
        }
        return this.password.equals(other.password);
    }

    /**
     *
     * @return the String representation of the User
     */
    @Override
    public String toString() {
        return String.format("Name: %s, Email: %s", name, email);
    }
}

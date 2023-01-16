package org.example.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private boolean isActive;
    private Timestamp createdTs;
    private Timestamp updatedTs;

    private Set<Role> roles;//TODO populate vio JDBC
    private Office office;
}

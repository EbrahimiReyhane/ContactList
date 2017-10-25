package entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="users")
public class UserEntity {
@Id
@Column(name = "username", nullable = false)
	private String username;
@Column(name = "password")
 private String password;
@Column(name = "role")
private String role;

public UserEntity() {
	super();
}

public UserEntity( String username, String password,String role) {
	super();
	this.role= role;
	this.username = username;
	this.password = password;
}


public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("************************************");
    sb.append("\nrole: ").append(role);
    sb.append("\nusername: ").append(username);
    sb.append("\npassword: ").append(password);
    sb.append("\n************************************");
    return sb.toString();
} 

}


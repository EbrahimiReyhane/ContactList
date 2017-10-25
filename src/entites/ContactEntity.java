package entites;

import javax.persistence.*;

@Entity
@Table(name="CONTACTS")
public class ContactEntity {
@Id
@GeneratedValue
@Column(name = "CONTACT_ID", unique = true, nullable = false)
	private int id;
@Column(name = "name")
	private String name;
@Column(name = "family")
	private String family;
@Column(name = "tel")
	private int tel;
@Column(name = "mobile")
	private int mobile;
@Column(name = "email")
	private String email;

public ContactEntity() {
	super();
}

public ContactEntity( String name, String family, int tel, int mobile, String email) {
	super();
	this.name = name;
	this.family = family;
	this.tel = tel;
	this.mobile = mobile;
	this.email = email;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getFamily() {
	return family;
}

public void setFamily(String family) {
	this.family = family;
}

public int getTel() {
	return tel;
}

public void setTel(int tel) {
	this.tel = tel;
}

public int getMobile() {
	return mobile;
}

public void setMobile(int mobile) {
	this.mobile = mobile;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("************************************");
    sb.append("\nid: ").append(id);
    sb.append("\nname: ").append(name);
    sb.append("\nfamily: ").append(family);
    sb.append("\ntel: ").append(tel);
    sb.append("\nmobile: ").append(mobile);
    sb.append("\n************************************");
    return sb.toString();
}

	

}

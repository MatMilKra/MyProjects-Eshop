package MyProjects.Eshop.Model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MessageSend {

	@Id
	@GeneratedValue
	private Integer id;
	private String date;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String subject;
	private String body;



	public MessageSend() {
		super();
	}

	public MessageSend(String date, String firstName, String lastName, String email, String phoneNumber, String subject,
			String body) {
		super();
		this.date = date;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.subject = subject;
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getId() {
		return id;
	}



	@Override
	public int hashCode() {
		return Objects.hash(body, date, email, firstName, id, lastName, phoneNumber, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageSend other = (MessageSend) obj;
		return Objects.equals(body, other.body) && Objects.equals(date, other.date)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(subject, other.subject);
	}

	@Override
	public String toString() {
		return  "Data: " + date + "\n" + 
				"Od: " + firstName + " " + lastName + "\n" + 
				"Email: " + email + "\n" +
				"Telefon: " + phoneNumber + "\n" +
				"Temat: " + subject	+ "\n" + 
				"treść: " + body;
	}

}

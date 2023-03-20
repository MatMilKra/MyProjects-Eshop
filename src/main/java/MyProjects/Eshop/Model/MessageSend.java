package MyProjects.Eshop.Model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MessageSend {

	@Id
	@GeneratedValue
	private Integer id;
	private String date;
	
	@ManyToOne(targetEntity = User.class)
	private User from;
	
	@ManyToOne(targetEntity = User.class)
	private User to;
	
	private String subject;
	private String body;



	public MessageSend() {
		super();
	}

	

	public MessageSend(String date, User from, User to, String subject, String body) {
		super();
		this.date = date;
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

	

	public User getFrom() {
		return from;
	}



	public void setFrom(User from) {
		this.from = from;
	}



	public User getTo() {
		return to;
	}



	public void setTo(User to) {
		this.to = to;
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
		return Objects.hash(body, date, from, id, subject, to);
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
		return Objects.equals(body, other.body) && Objects.equals(date, other.date) && Objects.equals(from, other.from)
				&& Objects.equals(id, other.id) && Objects.equals(subject, other.subject)
				&& Objects.equals(to, other.to);
	}



	@Override
	public String toString() {
		return "MessageSend [id=" + id + ", date=" + date + ", from=" + from + ", to=" + to + ", subject=" + subject
				+ ", body=" + body + "]";
	}



	

}

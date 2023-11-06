package MyProjects.Eshop.Model;

import java.util.Objects;

public class PreparedMail {
	String to;
	String subject;
	String body;
	public PreparedMail(String to, String subject, String body) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
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
	@Override
	public int hashCode() {
		return Objects.hash(body, subject, to);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreparedMail other = (PreparedMail) obj;
		return Objects.equals(body, other.body) && Objects.equals(subject, other.subject)
				&& Objects.equals(to, other.to);
	}
	@Override
	public String toString() {
		return "PreparedMail [to=" + to + ", subject=" + subject + ", body=" + body + "]";
	}
	
	
}

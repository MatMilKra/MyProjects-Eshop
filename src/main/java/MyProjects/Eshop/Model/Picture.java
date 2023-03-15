package MyProjects.Eshop.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Picture {
	@Id
	@GeneratedValue
	private int pictureId;
	private String name;

	public Picture() {
	}

	public Picture(String name) {
		super();
		this.name = name;
	}

	public int getPictureId() {
		return pictureId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
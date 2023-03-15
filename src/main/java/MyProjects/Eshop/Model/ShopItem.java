package MyProjects.Eshop.Model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ShopItem {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String description;
	private String category;
	private Double price;
	private Integer amount;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(targetEntity = User.class)
	private User vendor;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Picture> pictures;

	public ShopItem() {
		super();
	}

	public ShopItem(String name, String description, String category, Double price, Integer amount, User vendor) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.amount = amount;
		this.vendor = vendor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public User getVendor() {
		return vendor;
	}

	public void setVendor(User vendor) {
		this.vendor = vendor;
	}

	public Integer getId() {
		return id;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, category, description, id, name, vendor, pictures, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopItem other = (ShopItem) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(category, other.category)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(vendor, other.vendor)
				&& Objects.equals(pictures, other.pictures) && Objects.equals(price, other.price);
	}

	@Override
	public String toString() {
		return "- " + name + " (" + category + ", " + price + ") - ";
	}

}

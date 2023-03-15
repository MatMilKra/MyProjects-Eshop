package MyProjects.Eshop.Model;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private boolean activated;
	private Integer activateNum;





	@ManyToOne(cascade = CascadeType.PERSIST)
	private Role role;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<ShopItem> cartItems;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<ShopItem> buyedIytems;

	public User() {
		super();
	}

	public User(Integer id) {
		super();
		this.id = id;
	}

	public User(String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}


	public User(String username, String password, String firstName, String lastName, String email, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;

		activated = false;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idUser) {
		this.id = idUser;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public Integer getActivateNum() {
		return activateNum;
	}

	public void setActivateNum(Integer activateNum) {
		this.activateNum = activateNum;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

	public List<ShopItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<ShopItem> cartItems) {
		this.cartItems = cartItems;
	}

	public List<ShopItem> getBuyedIytems() {
		return buyedIytems;
	}

	public void setBuyedIytems(List<ShopItem> buyedIytems) {
		this.buyedIytems = buyedIytems;
	}

	public void addToCart(ShopItem item) {
		cartItems.add(item);
	}
	
	public void removeFromCart(ShopItem item) {
		cartItems.remove(item);
	}
	
	public void buy(ShopItem item) {
		cartItems.add(item);
	}

	@Override
	public int hashCode() {
		return Objects.hash(activateNum, activated, buyedIytems, cartItems, email, firstName, id, lastName, password,
				phoneNumber, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(activateNum, other.activateNum) && activated == other.activated
				&& Objects.equals(buyedIytems, other.buyedIytems) && Objects.equals(cartItems, other.cartItems)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(role, other.role) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", activated=" + activated + ", activateNum="
				+ activateNum + ", role=" + role + "]";
	}
	
	

}

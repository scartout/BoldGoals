package pl.scartout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import pl.scartout.model.Goal;
import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement
	@Entity
	@Table(name = "users")
	public final class User implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private Long id;
	    @Size(min = 3)
	    private String username;
	    @Size(min = 6)
	    private String password;
	    @Email
	    @Column(nullable = false)
	    private String email;
	    @OneToMany(mappedBy = "user", 
	            fetch = FetchType.EAGER,
	            cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
	            orphanRemoval = true)
	    private List<Goal> goals = new ArrayList<>();
	    @OneToOne
	    @JoinColumn(name = "role_id")
	    private Role role;
	    
	    public User(){}
	    
		public User(String username, String password, String email) {
			this.username = username;
			this.password = password;
			this.email = email;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<Goal> getGoals() {
			return goals;
		}

		public void setGoals(List<Goal> goals) {
			this.goals = goals;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((goals == null) ? 0 : goals.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((role == null) ? 0 : role.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
			return result;
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
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (goals == null) {
				if (other.goals != null)
					return false;
			} else if (!goals.equals(other.goals))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (role == null) {
				if (other.role != null)
					return false;
			} else if (!role.equals(other.role))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}
	   
}

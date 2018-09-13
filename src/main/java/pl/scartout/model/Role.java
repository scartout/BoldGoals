package pl.scartout.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement
	@Entity
	@Table(name = "roles")
	public final class Role implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @Column(name = "role_id")
	    private Long id;
	    @Column(nullable = false)
	    private String role;
	    
	    public Role(){}

		public Role(Long id, String role) {
			super();
			this.id = id;
			this.role = role;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "Role [id=" + id + ", role=" + role + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((role == null) ? 0 : role.hashCode());
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
			Role other = (Role) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (role == null) {
				if (other.role != null)
					return false;
			} else if (!role.equals(other.role))
				return false;
			return true;
		}

}

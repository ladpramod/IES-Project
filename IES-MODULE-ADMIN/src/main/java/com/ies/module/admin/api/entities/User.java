package com.ies.module.admin.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer userId;
	private String name;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String pazzd;	
	private String gender;
	private String mobile;
	private String dob;
	private Long ssn;
	private String activeSw;
	private String accStatus;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
			inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "roleId"))

	private Set<Role> roles = new HashSet<>();

	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name = "UPDATE_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;

	@JsonIgnore
	@OneToMany( mappedBy = "user",cascade = CascadeType.ALL)
	private Set<PlanEntity> plans = new HashSet<>() ;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
		

		return authorities;
	}

	@Override
	public String getPassword() {
		return this.pazzd;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

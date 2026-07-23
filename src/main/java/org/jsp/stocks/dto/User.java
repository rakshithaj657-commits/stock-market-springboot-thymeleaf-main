package org.jsp.stocks.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 3, max = 15, message = "* It Should be between 3~15 charecters")
	private String name;
	@Email(message = "* It Should be Valid Email")
	@NotEmpty(message = "* It is Required Field")
	private String email;
	@DecimalMin(value = "6000000000", message = "* It should be Proper Mobile Number")
	@DecimalMax(value = "9999999999", message = "* It should be Proper Mobile Number")
	private long mobile;
	@Past(message = "* Enter Proper DOB")
	@NotNull(message = "* It is Required Field")
	private LocalDate dob;
	@Pattern(
			 regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
			 message = "* Password must contain uppercase, lowercase, number, special character and minimum 8 characters"
			)

	private String password;
	@Transient
	@Pattern(
			 regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
			 message = "* Password must contain uppercase, lowercase, number, special character and minimum 8 characters"
			)
	private String confirmPassword;
	private int otp;
	private boolean verified;

	private double amount;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<UserStocksTransaction> transactions=new ArrayList<>();
	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

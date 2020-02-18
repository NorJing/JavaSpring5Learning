package com.learning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
*/

//@Data
//@RequiredArgsConstructor
//@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
  
	private String email; // final
	private List<Taco> tacos = new ArrayList<>();

	public Order() {}
	
	public Order(String email, List<Taco> tacos) {
		this.email = email;
		this.tacos = tacos;
	}
	
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}

	public List<Taco> getTacos() {
		return tacos;
	}

	public void setTacos(List<Taco> tacos) {
		this.tacos = tacos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Order [email=" + email + ", tacos=" + tacos + "]";
	}
	
	
    
}
  

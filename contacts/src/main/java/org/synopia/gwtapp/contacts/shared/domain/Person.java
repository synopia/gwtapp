package org.synopia.gwtapp.contacts.shared.domain;

import java.io.Serializable;
import java.util.Date;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.ComparisonChain;

public class Person implements Serializable, Comparable<Person> {

	private static final long serialVersionUID = 5376592111642092970L;

	public long id;
	public Gender gender;
//	@Size(min = 4, max = 255)
	public String name;
	public int weight = 30;
//	@NotNull
	public Date birthday;
	public Address address;
	public Boolean enable;

	public boolean isNew() {
		return id == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person other = (Person) obj;
			return Objects.equal(this.id, other.id);
		}
		return false;
	}

	@Override
	public String toString() {
		ToStringHelper helper = Objects.toStringHelper(this);
		helper.add("name", this.name);
		helper.add("adress", this.address);
		return helper.toString();
	}

	@Override
	public int compareTo(Person o) {
		if (o != null) {
			return ComparisonChain.start()
					.compare(name, o.name)
					.result();
		}
		return 1;
	}
}

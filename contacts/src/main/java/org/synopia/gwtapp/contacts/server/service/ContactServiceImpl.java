package org.synopia.gwtapp.contacts.server.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import com.google.inject.Singleton;
import org.synopia.gwtapp.contacts.shared.domain.Address;
import org.synopia.gwtapp.contacts.shared.domain.Contact;
import org.synopia.gwtapp.contacts.shared.domain.Gender;
import org.synopia.gwtapp.contacts.shared.domain.Group;
import org.synopia.gwtapp.contacts.shared.domain.Person;
import org.synopia.gwtapp.contacts.shared.service.ContactService;

public class ContactServiceImpl implements ContactService {

	private static final String[] FEMALE_FIRST_NAMES = {
			"Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan",
			"Margaret", "Dorothy", "Lisa", "Nancy", "Karen", "Betty", "Helen", "Sandra", "Donna",
			"Carol", "Ruth", "Sharon", "Michelle", "Laura", "Sarah", "Kimberly", "Deborah", "Jessica",
			"Shirley", "Cynthia", "Angela", "Melissa", "Brenda", "Amy", "Anna", "Rebecca", "Virginia",
			"Kathleen", "Pamela", "Martha", "Debra", "Amanda", "Stephanie", "Carolyn", "Christine",
			"Marie", "Janet", "Catherine", "Frances", "Ann", "Joyce", "Diane", "Alice", "Julie",
			"Heather", "Teresa", "Doris", "Gloria", "Evelyn", "Jean", "Cheryl", "Mildred", "Katherine",
			"Joan", "Ashley", "Judith", "Rose", "Janice", "Kelly", "Nicole", "Judy", "Christina",
			"Kathy", "Theresa", "Beverly", "Denise", "Tammy", "Irene", "Jane", "Lori", "Rachel",
			"Marilyn", "Andrea", "Kathryn", "Louise", "Sara", "Anne", "Jacqueline", "Wanda", "Bonnie",
			"Julia", "Ruby", "Lois", "Tina", "Phyllis", "Norma", "Paula", "Diana", "Annie", "Lillian",
			"Emily", "Robin", "Peggy", "Crystal", "Gladys", "Rita", "Dawn", "Connie", "Florence",
			"Tracy", "Edna", "Tiffany", "Carmen", "Rosa", "Cindy", "Grace", "Wendy", "Victoria", "Edith",
			"Kim", "Sherry", "Sylvia", "Josephine", "Thelma", "Shannon", "Sheila", "Ethel", "Ellen",
			"Elaine", "Marjorie", "Carrie", "Charlotte", "Monica", "Esther", "Pauline", "Emma",
			"Juanita", "Anita", "Rhonda", "Hazel", "Amber", "Eva", "Debbie", "April", "Leslie", "Clara",
			"Lucille", "Jamie", "Joanne", "Eleanor", "Valerie", "Danielle", "Megan", "Alicia", "Suzanne",
			"Michele", "Gail", "Bertha", "Darlene", "Veronica", "Jill", "Erin", "Geraldine", "Lauren",
			"Cathy", "Joann", "Lorraine", "Lynn", "Sally", "Regina", "Erica", "Beatrice", "Dolores",
			"Bernice", "Audrey", "Yvonne", "Annette", "June", "Samantha", "Marion", "Dana", "Stacy",
			"Ana", "Renee", "Ida", "Vivian", "Roberta", "Holly", "Brittany", "Melanie", "Loretta",
			"Yolanda", "Jeanette", "Laurie", "Katie", "Kristen", "Vanessa", "Alma", "Sue", "Elsie",
			"Beth", "Jeanne"
	};
	private static final String[] MALE_FIRST_NAMES = {
			"James", "John", "Robert", "Michael", "William", "David", "Richard", "Charles", "Joseph",
			"Thomas", "Christopher", "Daniel", "Paul", "Mark", "Donald", "George", "Kenneth", "Steven",
			"Edward", "Brian", "Ronald", "Anthony", "Kevin", "Jason", "Matthew", "Gary", "Timothy",
			"Jose", "Larry", "Jeffrey", "Frank", "Scott", "Eric", "Stephen", "Andrew", "Raymond",
			"Gregory", "Joshua", "Jerry", "Dennis", "Walter", "Patrick", "Peter", "Harold", "Douglas",
			"Henry", "Carl", "Arthur", "Ryan", "Roger", "Joe", "Juan", "Jack", "Albert", "Jonathan",
			"Justin", "Terry", "Gerald", "Keith", "Samuel", "Willie", "Ralph", "Lawrence", "Nicholas",
			"Roy", "Benjamin", "Bruce", "Brandon", "Adam", "Harry", "Fred", "Wayne", "Billy", "Steve",
			"Louis", "Jeremy", "Aaron", "Randy", "Howard", "Eugene", "Carlos", "Russell", "Bobby",
			"Victor", "Martin", "Ernest", "Phillip", "Todd", "Jesse", "Craig", "Alan", "Shawn",
			"Clarence", "Sean", "Philip", "Chris", "Johnny", "Earl", "Jimmy", "Antonio", "Danny",
			"Bryan", "Tony", "Luis", "Mike", "Stanley", "Leonard", "Nathan", "Dale", "Manuel", "Rodney",
			"Curtis", "Norman", "Allen", "Marvin", "Vincent", "Glenn", "Jeffery", "Travis", "Jeff",
			"Chad", "Jacob", "Lee", "Melvin", "Alfred", "Kyle", "Francis", "Bradley", "Jesus", "Herbert",
			"Frederick", "Ray", "Joel", "Edwin", "Don", "Eddie", "Ricky", "Troy", "Randall", "Barry",
			"Alexander", "Bernard", "Mario", "Leroy", "Francisco", "Marcus", "Micheal", "Theodore",
			"Clifford", "Miguel", "Oscar", "Jay", "Jim", "Tom", "Calvin", "Alex", "Jon", "Ronnie",
			"Bill", "Lloyd", "Tommy", "Leon", "Derek", "Warren", "Darrell", "Jerome", "Floyd", "Leo",
			"Alvin", "Tim", "Wesley", "Gordon", "Dean", "Greg", "Jorge", "Dustin", "Pedro", "Derrick",
			"Dan", "Lewis", "Zachary", "Corey", "Herman", "Maurice", "Vernon", "Roberto", "Clyde",
			"Glen", "Hector", "Shane", "Ricardo", "Sam", "Rick", "Lester", "Brent", "Ramon", "Charlie",
			"Tyler", "Gilbert", "Gene"
	};
	private static final String[] LAST_NAMES = {
			"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore",
			"Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia",
			"Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen",
			"Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker",
			"Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips",
			"Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris",
			"Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper",
			"Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James",
			"Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross",
			"Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes",
			"Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander",
			"Russell", "Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham", "Sullivan",
			"Wallace", "Woods", "Cole", "West", "Jordan", "Owens", "Reynolds", "Fisher", "Ellis",
			"Harrison", "Gibson", "Mcdonald", "Cruz", "Marshall", "Ortiz", "Gomez", "Murray", "Freeman",
			"Wells", "Webb", "Simpson", "Stevens", "Tucker", "Porter", "Hunter", "Hicks", "Crawford",
			"Henry", "Boyd", "Mason", "Morales", "Kennedy", "Warren", "Dixon", "Ramos", "Reyes", "Burns",
			"Gordon", "Shaw", "Holmes", "Rice", "Robertson", "Hunt", "Black", "Daniels", "Palmer",
			"Mills", "Nichols", "Grant", "Knight", "Ferguson", "Rose", "Stone", "Hawkins", "Dunn",
			"Perkins", "Hudson", "Spencer", "Gardner", "Stephens", "Payne", "Pierce", "Berry",
			"Matthews", "Arnold", "Wagner", "Willis", "Ray", "Watkins", "Olson", "Carroll", "Duncan",
			"Snyder", "Hart", "Cunningham", "Bradley", "Lane", "Andrews", "Ruiz", "Harper", "Fox",
			"Riley", "Armstrong", "Carpenter", "Weaver", "Greene", "Lawrence", "Elliott", "Chavez",
			"Sims", "Austin", "Peters", "Kelley", "Franklin", "Lawson"
	};
	private static final String[] STREET_NAMES = {
			"Peachtree", "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Tenth",
			"Fourteenth", "Spring", "Techwood", "West Peachtree", "Juniper", "Cypress", "Fowler",
			"Piedmont", "Juniper", "Main", "Central", "Currier", "Courtland", "Williams",
			"Centennial", "Olympic", "Baker", "Highland", "Pryor", "Decatur", "Bell", "Edgewood",
			"Mitchell", "Forsyth", "Capital"
	};
	private static final String[] STREET_SUFFIX = {
			"St", "Rd", "Ln", "Blvd", "Way", "Pkwy", "Cir", "Ave"
	};
	private static final String[] CITIES = {
			"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego"
	};
	private static final String[] DOMAINS = {
			"gmail.com", "google.com", "yahoo.com", "aol.com", "msn.com", "comcast.net", "cox.net", "sbcgloval.net"
	};

	private static final AtomicLong SEQUENCE = new AtomicLong();

	private final List<Contact> contacts = Lists.newArrayList();
	private final List<Group> groups = Lists.newArrayList();
	private final Group groupAll = new Group("All");

	public ContactServiceImpl() {
		groups.add(new Group("Familly"));
		groups.add(new Group("Friend"));
		groups.add(new Group("Colleague"));
		for (int i = 0; i < 50; i++) {
			savePerson(createRandom());
		}
		groups.add(0, groupAll);
		Collections.sort(groupAll.members);
	}

	@Override
	public List<Group> getGroups() {
		return Lists.newArrayList(this.groups);
	}

	@Override
	public List<Contact> getPeople() {
		return Lists.newArrayList(this.contacts);
	}

	@Override
	public Contact getPerson(final String name) {
		return Iterables.find(this.contacts, new Predicate<Person>() {

			@Override
			public boolean apply(Person input) {
				return name != null && name.equals(input.name);
			}
		}, null);
	}

	@Override
	public Contact savePerson(Contact contact) {
		if (contact.isNew()) {
			contact.id = (SEQUENCE.incrementAndGet());
		}
		this.contacts.remove(contact);
		this.contacts.add(contact);
		Collections.sort(contacts);

		groupAll.members.remove(contact);
		for (Group group : groups) {
			group.members.remove(contact);
			if (contact.groups.contains(group.name)) {
				group.members.add(contact);
			}
			Collections.sort(group.members);
		}
		groupAll.members.add(contact);
		Collections.sort(groupAll.members);
		return contact;
	}

	private Contact createRandom() {
		Contact contact = new Contact();
		if (new Random().nextBoolean()) {
			contact.name = (nextValue(LAST_NAMES) + " " + nextValue(MALE_FIRST_NAMES));
			contact.gender = (Gender.MALE);
		}
		else {
			contact.name = (nextValue(LAST_NAMES) + " " + nextValue(FEMALE_FIRST_NAMES));
			contact.gender = (Gender.FEMALE);
		}

		// Create a birthday between 20-80 years ago.
		int year = (new Date()).getYear() - 21 - new Random().nextInt(61);
		contact.birthday = (new Date(year, new Random().nextInt(12), 1 + new Random().nextInt(31)));

		// Create a weight between 30 and 120
		contact.weight = (30 + new Random().nextInt(90));

		// Create an address.
		Address address = new Address();
		address.street = (nextValue(STREET_NAMES));
		address.postCode = (nextValue(STREET_SUFFIX));
		address.city = (nextValue(CITIES));
		contact.address = (address);

		contact.emails.add(contact.name.replace(" ", ".").toLowerCase() + "@" + nextValue(DOMAINS));

		Group memberOf = groups.get(new Random().nextInt(groups.size()));
		memberOf.members.add(contact);
		groupAll.members.add(contact);

		contact.groups.add(memberOf.name);

		return contact;

	}

	private <T> T nextValue(T[] array) {
		return array[new Random().nextInt(array.length)];
	}

}

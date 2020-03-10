public class Employee implements Cloneable  {
	
	private String forename;
	private String surname;
	private String title;
	private float salary;
	
	public Employee(){ // default constructor
		this.forename = "";
		this.surname = "";
		this.title = "";
		this.salary = 0.0f;
	}
	
	public Employee (String forename, String surname, String title, float salary){ // Parameter constructor
		this.forename = forename;
		this.surname = surname;
		this.title =  title;
		this.salary = salary;
	}
	
	public Employee(Employee employee){ //Copy constructor
		this.forename = employee.forename;
		this.surname = employee.surname;
		this.title = employee.title;
		this.salary = employee.salary;	
	}

	public String getForename() { //getter method
		return forename;
	}

	public void setForename(String forename) { //setter method
		this.forename = forename;
	}

	public String getSurname() { //getter method
		return surname;
	}

	public void setSurname(String surname) { //setter method
		this.surname = surname;
	}

	public String getTitle() { //getter method
		return title;
	}

	public void setTitle(String title) { //setter method
		this.title = title;
	}

	public float getSalary() { //getter method
		return salary;
	}

	public void setSalary(float salary) { //setter method
		this.salary = salary;
	}
	public String toString(){
		String s = new String("Forename: "+ forename + ", Surname: " + surname + ", Job Title: " + title + ", Salary: " + salary);
		return s;
	}
	
	public Object clone(){ //clone method
		Employee employee = new Employee(this);
		return employee;
	}

}

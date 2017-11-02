import java.sql.Date;

public class Person {
	private String m_firstname;
	private String m_surname;
	private Date m_dob;
	private String m_achievement;
	
	
	
	public String getFirstName() {
		return m_firstname;
	}
	
	public String getSurname() {
		return m_surname;
	}
	
	public String getDob() {
		Integer d = m_dob.getDate();
		String ds;
		if (d<10) ds = "0"+d.toString();
		else ds = d.toString();
		Integer m = m_dob.getMonth()+1;
		String ms;
		if (m<10) ms = "0"+m.toString();
		else ms = m.toString();
		int y = m_dob.getYear()+1900;
		return ds+"/"+ms+"/"+y;
	}
	
	public String getAchievement() {
		return m_achievement;
	}

	// v1 interface
	public Person(String firstname,String surname) {
		m_firstname = firstname;
		m_surname = surname;
		m_dob = null;
		m_achievement = null;
	}
	
	// v2 interface
	public Person(String firstname,String surname, Date dob) {
		m_firstname = firstname;
		m_surname = surname;
		m_dob = dob;
		m_achievement = null;
		
	}
	
	// v3 interface
	public Person(String firstname,String surname, Date dob,String achievement) {
		m_firstname = firstname;
		m_surname = surname;
		m_dob = dob;
		m_achievement = achievement;
		
	}
}

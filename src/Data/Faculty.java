package Data;

import java.util.ArrayList;

public class Faculty {
	private String FacultyCode;
	private String Name;
	ArrayList<String> Department = new ArrayList<>();
	ArrayList<String> Courses = new ArrayList<>();
	private String AreaOfExperties;
	private String ProfessionalInterst;
	
	public String getFacultyCode() {
		return FacultyCode;
	}
	public String getName() {
		return Name;
	}
	public String getAreaOfExperties() {
		return AreaOfExperties;
	}
	public String getProfessionalInterst() {
		return ProfessionalInterst;
	}
	
	public Faculty(String facultyCode, String name, String areaOfExperties, String professionalInterst) {
		FacultyCode = facultyCode;
		Name = name;
		AreaOfExperties = areaOfExperties;
		ProfessionalInterst = professionalInterst;
	}
	public Faculty() {
		// TODO Auto-generated constructor stub
	}
	public Faculty(String facultyCode, String name) {
		FacultyCode = facultyCode;
		Name = name;
	}
	
}

package Data;

import java.util.ArrayList;

public class Department {
	private String DepartmentName;
	private String DepartmentCode;
	ArrayList<String> CourseName = new ArrayList<>();
	
	public String getDepartmentName() {
		return DepartmentName;
	}

	public String getDepartmentCode() {
		return DepartmentCode;
	}

	public Department(String departmentCode, String departmentName) {
		DepartmentCode = departmentCode;
		DepartmentName = departmentName;
	}

	public Department() {
		super();
	}
	
}

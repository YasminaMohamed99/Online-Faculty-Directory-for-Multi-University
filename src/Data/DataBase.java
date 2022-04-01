package Data;

import java.sql.*;
import java.util.ArrayList;

import Admin.Admin;

public class DataBase {
	PreparedStatement stmt = null;

	public Boolean verify(String username, String Password) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		Admin admin = new Admin(username, Password);
		Boolean isFound = false;
		String sql = "select * from admin where UserName = ? or Password = ?";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, admin.getUsername());
			stmt.setString(2, admin.getPassword());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				isFound = true;
			} else {
				isFound = false;
			}
		} catch (SQLException e) {
		}
		return isFound;
	}
	public Boolean verifyFaculty(String facultycode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		Boolean isFound = false;
		String sql = "select * from faculty where FacultyCode = ?";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, facultycode);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				isFound = true;
			} else {
				isFound = false;
			}
		} catch (SQLException e) {
		}
		return isFound;
	}
	
	public Boolean verifyFacultyName(String name) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		Boolean isFound = false;
		String sql = "select * from faculty where Name = ?";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				isFound = true;
			} else {
				isFound = false;
			}
		} catch (SQLException e) {
		}
		return isFound;
	}

	public void insertFaculty(Faculty faculty) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String sql = "INSERT INTO faculty (FacultyCode, Name, AreaOfExperties, ProfissionalInterst) VALUES (?, ?, ?, ?)";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, faculty.getFacultyCode());
			stmt.setString(2, faculty.getName());
			stmt.setString(3, faculty.getAreaOfExperties());
			stmt.setString(4, faculty.getProfessionalInterst());

			stmt.executeUpdate();
			System.out.println("SUCCESS!\n");

		} catch (SQLException e) {
		}
	}

	public void insertDepartment(Department department) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String sql = "INSERT INTO department (DepartmentCode, DepartmentName) VALUES (?, ?)";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, department.getDepartmentCode());
			stmt.setString(2, department.getDepartmentName());

			stmt.executeUpdate();
			System.out.println("SUCCESS!\n");

		} catch (SQLException e) {
		}
	}

	public void insertCourses(Courses course) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String sql = "INSERT INTO course (CourseCode, CourseName) VALUES (?, ?)";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, course.getCourseCode());
			stmt.setString(2, course.getCourseName());

			stmt.executeUpdate();
			System.out.println("SUCCESS!\n");

		} catch (SQLException e) {
		}
	}

	public void insertdepartment_faculty(String Departmentcode, String Facultycode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String sql = "INSERT INTO department_faculty (DepartmentCode, FacultyID) VALUES (?, ?)";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, Departmentcode);
			stmt.setString(2, Facultycode);

			stmt.executeUpdate();
			System.out.println("SUCCESS!\n");

		} catch (SQLException e) {
		}
	}

	public void insertcourse_faculty(String Coursecode, String Facultycode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String sql = "INSERT INTO faculty_course (facultyID, CourseCode) VALUES (?, ?)";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, Facultycode);
			stmt.setString(2, Coursecode);

			stmt.executeUpdate();
			System.out.println("SUCCESS!\n");

		} catch (SQLException e) {
		}
	}

	public void insertdepartment_course(String Coursecode, String Departmentcode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String sql = "INSERT INTO department_course (DepartmentCode, CourseCode) VALUES (?, ?)";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, Departmentcode);
			stmt.setString(2, Coursecode);

			stmt.executeUpdate();
			System.out.println("SUCCESS!\n");

		} catch (SQLException e) {
		}
	}

	public ArrayList<String> selectAlldepartment(String DepartmentCode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<String> department = new ArrayList<String>();
		String sql = "SELECT DepartmentName FROM department WHERE DepartmentCode = ?";

		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, DepartmentCode);
			ResultSet rs = (ResultSet) stmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("DepartmentName"));
				department.add(rs.getString("DepartmentName"));
			}
		} catch (SQLException e) {
		}
		return department;
	}

	public ArrayList<String> selectFacultydepartment(String FacultyCode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<String> department = new ArrayList<String>();
		String sql = "SELECT * FROM department_faculty WHERE FacultyID = ?";

		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, FacultyCode);
			ResultSet rs = (ResultSet) stmt.executeQuery();

			while (rs.next()) {
				department = selectAlldepartment(rs.getString("DepartmentCode"));
			}
		} catch (SQLException e) {
		}
		return department;
	}
	
	public ArrayList<String> selectFacultyD(String FacultyCode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<String> department = new ArrayList<String>();
		String sql = "SELECT * FROM department_faculty WHERE FacultyID = ?";

		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, FacultyCode);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				department.add(rs.getString("DepartmentCode"));
			}
		} catch (SQLException e) {
		}
		return department;
	}

	public ArrayList<String> selectAllcourse(String CourseCode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<String> courses = new ArrayList<String>();
		String sql = "SELECT CourseName FROM course WHERE CourseCode = ?";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, CourseCode);
			ResultSet rs = (ResultSet) stmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("CourseName"));
				courses.add(rs.getString("DepartmentName"));
			}
		} catch (SQLException e) {
		}
		return courses;
	}

	public ArrayList<String> selectFacultycourses(String FacultyCode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<String> courses = new ArrayList<String>();
		String sql = "SELECT * FROM faculty_course WHERE facultyID = ?";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, FacultyCode);
			ResultSet rs = (ResultSet) stmt.executeQuery();

			while (rs.next()) {
				courses = selectAllcourse(rs.getString("CourseCode"));
			}
		} catch (SQLException e) {
		}
		return courses;
	}
	
	public ArrayList<String> selectFacultyC(String FacultyCode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<String> courses = new ArrayList<String>();
		String sql = "SELECT * FROM faculty_course WHERE facultyID = ?";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, FacultyCode);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				courses.add(rs.getString("CourseCode"));
			}
		} catch (SQLException e) {
		}
		return courses;
	}

	public ArrayList<Faculty> selectAll() throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<Faculty> faculties = new ArrayList<Faculty>();
		String sql = "SELECT * FROM faculty ";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				System.out.println("Faculty Code: " + rs.getString("FacultyCode"));
				System.out.println("Faculty Name: " + rs.getString("Name"));
				System.out.println();
				System.out.println("Faculty Departments: ");
				ArrayList<String> department = new ArrayList<String>();
				department = selectFacultydepartment(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Faculty Courses: ");
				ArrayList<String> courses = new ArrayList<String>();
				courses = selectFacultycourses(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Area Of Experties: " + rs.getString("AreaOfExperties"));
				System.out.println("Profissional Interst: " + rs.getString("ProfissionalInterst"));
				System.out.println("*********************************************************************");

				Faculty faculty = new Faculty();
				faculty = new Faculty(rs.getString("FacultyCode"), rs.getString("Name"),
						rs.getString("AreaOfExperties"), rs.getString("ProfissionalInterst"));
				faculties.add(faculty);
			}
		} catch (SQLException e) {
		}
		return faculties;
	}
	

	public void UpdateFacultyName(String Facultycode, String newname) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String sql = "UPDATE faculty SET Name = ? WHERE FacultyCode = ?";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, newname);
			stmt.setString(2, Facultycode);
			stmt.executeUpdate();
			System.out.println("UPDATED!\n");
		} catch (SQLException e) {
		}
	}

	public void UpdateFacultyArea(String Facultycode, String newArea) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String sql = "UPDATE faculty SET AreaOfExperties = ? WHERE FacultyCode = ?";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, newArea);
			stmt.setString(2, Facultycode);
			stmt.executeUpdate();
			System.out.println("UPDATED!\n");
		} catch (SQLException e) {
		}
	}

	public void deleteFaculty(String facultycode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		try {
			String sql = "DELETE FROM faculty WHERE FacultyCode = ?";
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, facultycode);
			stmt.executeUpdate();

			deleteCourse(facultycode);
			
			deletecourse(facultycode);
			
			deleteDepartment_courses(facultycode);
			
			deleteDepartment(facultycode);
			
			deletedepartment(facultycode);

			System.out.println("Deleted!");
		} catch (SQLException ex) {
		}
	}

	public void deleteDepartment(String facultycode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<String> codes = selectFacultyD(facultycode);
		String departmentcode = "";
		for (int i = 0; i < codes.size(); i++) {
			departmentcode = codes.get(i);
			try {
				String sql = "DELETE FROM department WHERE DepartmentCode = ?";
				stmt = db.getConnection().prepareStatement(sql);
				stmt.setString(1, departmentcode);
				stmt.executeUpdate();
			} catch (SQLException ex) {
			}
		}
	}

	public void deleteCourse(String facultycode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<String> codes = new ArrayList<>();
		codes = selectFacultyC(facultycode);
		String coursecode = "";
		for (int i = 0; i < codes.size(); i++) {
			coursecode = codes.get(i);
			try {
				String sql = "DELETE FROM course WHERE CourseCode = ?";
				stmt = db.getConnection().prepareStatement(sql);
				stmt.setString(1, coursecode);
				stmt.executeUpdate();
			} catch (SQLException ex) {
			}
		}
	}

	public void deleteDepartment_courses(String facultycode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<String> codes = selectFacultyD(facultycode);
		String departmentcode = "";
		for (int i = 0; i < codes.size(); i++) {
			departmentcode = codes.get(i);
			try {
				String sql = "DELETE FROM department_course WHERE DepartmentCode = ?";
				stmt = db.getConnection().prepareStatement(sql);
				stmt.setString(1, departmentcode);
				stmt.executeUpdate();
			} catch (SQLException ex) {
			}
		}
	}

	public void deletedepartment(String facultycode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		try {
			String sql = "DELETE FROM department_faculty WHERE FacultyID = ?";
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, facultycode);
			stmt.executeUpdate();
		} catch (SQLException ex) {
		}
	}

	public void deletecourse(String facultycode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		try {
			String sql = "DELETE FROM faculty_course WHERE facultyID = ?";
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, facultycode);
			stmt.executeUpdate();
		} catch (SQLException ex) {
		}
	}

	public void deletedepartmentcourse(String departmentcode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		try {
			String sql = "DELETE FROM department_course WHERE DepartmentCode = ?";
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, departmentcode);
			stmt.executeUpdate();
		} catch (SQLException ex) {
		}
	}

	public ArrayList<Faculty> selectFacultyByName(String Name) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<Faculty> faculties = new ArrayList<Faculty>();
		String sql = "SELECT * FROM faculty WHERE Name = ? ";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, Name);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				System.out.println("Faculty Code: " + rs.getString("FacultyCode"));
				System.out.println("Faculty Name: " + rs.getString("Name"));
				System.out.println();
				System.out.println("Faculty Departments: ");
				ArrayList<String> department = new ArrayList<String>();
				department = selectFacultydepartment(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Faculty Courses: ");
				ArrayList<String> courses = new ArrayList<String>();
				courses = selectFacultycourses(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Area Of Experties: " + rs.getString("AreaOfExperties"));
				System.out.println("Profissional Interst: " + rs.getString("ProfissionalInterst"));
				System.out.println("*********************************************************************");

				Faculty faculty = new Faculty();
				faculty = new Faculty(rs.getString("FacultyCode"), rs.getString("Name"),
						rs.getString("AreaOfExperties"), rs.getString("ProfissionalInterst"));
				faculties.add(faculty);
			}
		} catch (SQLException e) {
		}
		return faculties;
	}

	public String selectdepartmentcode(String DepartmentName) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String department = "";
		String sql = "SELECT DepartmentCode FROM department WHERE DepartmentName = ?";

		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, DepartmentName);
			ResultSet rs = (ResultSet) stmt.executeQuery();

			while (rs.next()) {
				department = selectfacultycode(rs.getString("DepartmentCode"));
			}
		} catch (SQLException e) {
		}
		return department;
	}

	public String selectcoursecode(String CourseName) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String course = "";
		String sql = "SELECT CourseCode FROM course WHERE CourseName = ?";

		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, CourseName);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				course = selectfacultycodebycourse(rs.getString("CourseCode"));
			}
		} catch (SQLException e) {
		}
		return course;
	}

	public String selectfacultycode(String Departmentcode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String faculty = "";
		String sql = "SELECT FacultyID FROM department_faculty WHERE DepartmentCode = ?";

		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, Departmentcode);
			ResultSet rs = (ResultSet) stmt.executeQuery();

			while (rs.next()) {
				faculty = rs.getString("FacultyID");
			}
		} catch (SQLException e) {
		}
		return faculty;
	}

	public String selectfacultycodebycourse(String Coursecode) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String faculty = "";
		String sql = "SELECT facultyID FROM faculty_course WHERE CourseCode = ?";

		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, Coursecode);
			ResultSet rs = (ResultSet) stmt.executeQuery();

			while (rs.next()) {
				faculty = rs.getString("facultyID");
			}
		} catch (SQLException e) {
		}
		return faculty;
	}

	public ArrayList<Faculty> selectFacultyBycourse(String courseName) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String facultycode = "";
		ArrayList<Faculty> faculties = new ArrayList<Faculty>();
		facultycode = selectcoursecode(courseName);
		String sql = "SELECT * FROM faculty WHERE FacultyCode = ? ";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, facultycode);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				System.out.println("Faculty Code: " + rs.getString("FacultyCode"));
				System.out.println("Faculty Name: " + rs.getString("Name"));
				System.out.println();
				System.out.println("Faculty Departments: ");
				ArrayList<String> department = new ArrayList<String>();
				department = selectFacultydepartment(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Faculty Courses: ");
				ArrayList<String> courses = new ArrayList<String>();
				courses = selectFacultycourses(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Area Of Experties: " + rs.getString("AreaOfExperties"));
				System.out.println("Profissional Interst: " + rs.getString("ProfissionalInterst"));
				System.out.println("*********************************************************************");

				Faculty faculty = new Faculty();
				faculty = new Faculty(rs.getString("FacultyCode"), rs.getString("Name"),
						rs.getString("AreaOfExperties"), rs.getString("ProfissionalInterst"));
				faculties.add(faculty);
			}
		} catch (SQLException e) {
		}
		return faculties;

	}

	public ArrayList<Faculty> selectFacultyByDeb(String depName) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		String facultycode = "";
		ArrayList<Faculty> faculties = new ArrayList<Faculty>();
		facultycode = selectdepartmentcode(depName);
		String sql = "SELECT * FROM faculty WHERE FacultyCode = ? ";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, facultycode);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				System.out.println("Faculty Code: " + rs.getString("FacultyCode"));
				System.out.println("Faculty Name: " + rs.getString("Name"));
				System.out.println();
				System.out.println("Faculty Departments: ");
				ArrayList<String> department = new ArrayList<String>();
				department = selectFacultydepartment(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Faculty Courses: ");
				ArrayList<String> courses = new ArrayList<String>();
				courses = selectFacultycourses(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Area Of Experties: " + rs.getString("AreaOfExperties"));
				System.out.println("Profissional Interst: " + rs.getString("ProfissionalInterst"));
				System.out.println("*********************************************************************");

				Faculty faculty = new Faculty();
				faculty = new Faculty(rs.getString("FacultyCode"), rs.getString("Name"),
						rs.getString("AreaOfExperties"), rs.getString("ProfissionalInterst"));
				faculties.add(faculty);
			}
		} catch (SQLException e) {
		}
		return faculties;
	}

	public ArrayList<Faculty> selectFacultyByArea(String Area) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<Faculty> faculties = new ArrayList<Faculty>();
		String sql = "";
		String sqlArea = "SELECT * FROM faculty";
		String areaOfE = "";
		stmt = db.getConnection().prepareStatement(sqlArea);
		ResultSet res = stmt.executeQuery();
		ArrayList<String> areas = new ArrayList<>();
		while (res.next()) {
			if((res.getString("AreaOfExperties").contains(Area))) {
				sql = "SELECT * FROM faculty WHERE AreaOfExperties = ? ";
				areaOfE = res.getString("AreaOfExperties");
				break;
			}
		}
		
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, areaOfE);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				System.out.println("Faculty Code: " + rs.getString("FacultyCode"));
				System.out.println("Faculty Name: " + rs.getString("Name"));
				System.out.println();
				System.out.println("Faculty Departments: ");
				ArrayList<String> department = new ArrayList<String>();
				department = selectFacultydepartment(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Faculty Courses: ");
				ArrayList<String> courses = new ArrayList<String>();
				courses = selectFacultycourses(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Area Of Experties: " + rs.getString("AreaOfExperties"));
				System.out.println("Profissional Interst: " + rs.getString("ProfissionalInterst"));
				System.out.println("*********************************************************************");

				Faculty faculty = new Faculty();
				faculty = new Faculty(rs.getString("FacultyCode"), rs.getString("Name"),
						rs.getString("AreaOfExperties"), rs.getString("ProfissionalInterst"));
				faculties.add(faculty);
			}
		} catch (SQLException e) {
		}
		return faculties;
	}

	public ArrayList<Faculty> selectFacultyByProfissional(String prof) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ArrayList<Faculty> faculties = new ArrayList<Faculty>();
		String sql = "SELECT * FROM faculty WHERE ProfissionalInterst = ? ";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setString(1, prof);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				System.out.println("Faculty Code: " + rs.getString("FacultyCode"));
				System.out.println("Faculty Name: " + rs.getString("Name"));
				System.out.println();
				System.out.println("Faculty Departments: ");
				ArrayList<String> department = new ArrayList<String>();
				department = selectFacultydepartment(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Faculty Courses: ");
				ArrayList<String> courses = new ArrayList<String>();
				courses = selectFacultycourses(rs.getString("FacultyCode"));
				System.out.println();

				System.out.println("Area Of Experties: " + rs.getString("AreaOfExperties"));
				System.out.println("Profissional Interst: " + rs.getString("ProfissionalInterst"));
				System.out.println("*********************************************************************");

				Faculty faculty = new Faculty();
				faculty = new Faculty(rs.getString("FacultyCode"), rs.getString("Name"),
						rs.getString("AreaOfExperties"), rs.getString("ProfissionalInterst"));
				faculties.add(faculty);
			}
		} catch (SQLException e) {
		}
		return faculties;
	}

}

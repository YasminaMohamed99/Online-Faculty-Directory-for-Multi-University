package Admin;

import java.sql.SQLException;
import java.util.Scanner;

import Data.Courses;
import Data.DataBase;
import Data.Department;
import Data.Faculty;

public class Admin {
	private String username;
	private String password;
	DataBase data = new DataBase();
	Faculty faculty = new Faculty();
	Department department = new Department();
	Courses course = new Courses();
	Scanner intScan = new Scanner(System.in);
	Scanner stringScan = new Scanner(System.in);

	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean login() throws SQLException {
		System.out.print("Enter Username: ");
		username = stringScan.nextLine();

		System.out.print("Enter Password: ");
		password = stringScan.nextLine();

		Boolean isFound = data.verify(username, password);
		return isFound;
	}

	public void AddFaculty() throws SQLException {
		System.out.print("Enter Faculty Code:");
		String facultycode = stringScan.nextLine();

		System.out.print("Enter Faculty Name:");
		String facultyname = stringScan.nextLine();

		System.out.println();
		System.out.println("Enter Faculty Department!");

		while (true) {
			System.out.println("To Add Department Press 1");
			System.out.println("To Exit Press 0");
			int c = intScan.nextInt();
			System.out.println();

			if (c == 1) {
				System.out.print("Enter Department Code:");
				String departmentcode = stringScan.nextLine();

				System.out.print("Enter Department Name:");
				String departmentname = stringScan.nextLine();

				department = new Department(departmentcode, departmentname);
				data.insertDepartment(department);
				data.insertdepartment_faculty(departmentcode, facultycode);

				System.out.println();
				System.out.println("Enter Department Courses!");
				while (true) {
					System.out.println("To Add Courses Press 1");
					System.out.println("To Exit Press 0");
					int c1 = intScan.nextInt();
					System.out.println();

					if (c1 == 1) {
						System.out.print("Enter Course Code:");
						String coursecode = stringScan.nextLine();

						System.out.print("Enter Course Name:");
						String coursename = stringScan.nextLine();

						course = new Courses(coursecode, coursename);
						data.insertCourses(course);
						data.insertdepartment_course(coursecode, departmentcode);
						data.insertcourse_faculty(coursecode, facultycode);

						System.out.println();

					} else if (c1 == 0)
						break;
					else {
						System.out.println("The Choice is incorrect please enter the correct choice!");
						continue;
					}
				}
			} else if (c == 0)
				break;
			else {
				System.out.println("The Choice is incorrect please enter the correct choice!");
				continue;
			}
		}
		System.out.print("Enter Faculty of Area of Experties:");
		String facultyArea = stringScan.nextLine();

		System.out.print("Enter Faculty Professional Interest:");
		String facultyprof = stringScan.nextLine();

		faculty = new Faculty(facultycode, facultyname, facultyArea, facultyprof);
		data.insertFaculty(faculty);
	}

	public void ViewFaculty() throws SQLException {
		data.selectAll();
	}

	public void UpdateFacultyName() throws SQLException {
		System.out.print("Enter Faculty Code that you want to update:");
		String facultycode = stringScan.nextLine();

		if (data.verifyFaculty(facultycode)) {
			System.out.print("Enter New Faculty Name:");
			String facultyname = stringScan.nextLine();

			data.UpdateFacultyName(facultycode, facultyname);
		} else {
			System.out.println("Faculty is not found!");
		}

	}

	public void UpdateFacultyArea() throws SQLException {
		System.out.print("Enter Faculty Code that you want to update:");
		String facultycode = stringScan.nextLine();

		if (data.verifyFaculty(facultycode)) {
			System.out.print("Enter New Faculty of Area of Experties:");
			String facultyArea = stringScan.nextLine();

			data.UpdateFacultyArea(facultycode, facultyArea);
		} else {
			System.out.println("Faculty is not found!");
		}
	}

	public void DeleteFaculty() throws SQLException {
		System.out.print("Enter Faculty Code that you want deleted:");
		String facultycode = stringScan.nextLine();
		if(data.verifyFaculty(facultycode)) {
			data.deleteFaculty(facultycode);			
		} else {
			System.out.println("Faculty is not found!");
		}
	}
}

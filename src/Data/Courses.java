package Data;

public class Courses {
	private String CourseCode;
	private String CourseName;
	
	public String getCourseCode() {
		return CourseCode;
	}

	public String getCourseName() {
		return CourseName;
	}

	public Courses(String courseCode, String courseName) {
		CourseCode = courseCode;
		CourseName = courseName;
	}

	public Courses() {
		// TODO Auto-generated constructor stub
	}
	
}

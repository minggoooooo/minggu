package model;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnector;

public class CourseDAO extends DBConnector {

	public CourseDAO() {
		super();
	}

	public int SettingId() {
		int result = 0;

		try {
			String sql = "select * from course order by id desc";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = Integer.parseInt(rs.getString(1).substring(12));
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// 리소스(close)를 해제
			try {
				if (rs != null) {
					rs.close();
				}
				if (psmt != null) {
					psmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int AddCourse(Course course) {
		int result = 0;

		try {
			String sql = "insert into course (id,location,length,expect_time,difficulty,file_id) values(?,?,?,?,?,?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, course.getId());
			psmt.setString(2, course.getLocation());
			psmt.setString(3, course.getLength());
			psmt.setString(4, course.getExpect_time());
			psmt.setString(5, course.getDifficulty());
			psmt.setString(6, course.getFile_id());
			result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Course> LoadCourse() {
		ArrayList<Course> courses = new ArrayList<>();

		try {
			String sql = "select * from course order by id";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getString(1));
				course.setLocation(rs.getString(2));
				course.setLength(rs.getString(3));
				course.setExpect_time(rs.getString(4));
				course.setDifficulty(rs.getString(5));
				course.setFile_id(rs.getString(6));
				courses.add(course);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	public ArrayList<Course> LoadCourseTimeDesc() {
		ArrayList<Course> courses = new ArrayList<>();

		try {
			String sql = "SELECT * FROM course " + "ORDER BY " + "  CASE "
					+ "    WHEN expect_time LIKE '%시간%' AND expect_time LIKE '%분%' THEN "
					+ "      CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(expect_time, '시간', 1), ' ', -1) AS DECIMAL(5,2)) * 60 + "
					+ "      CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(expect_time, '분', -1), ' ', -1) AS DECIMAL(5,2)) "
					+ "    WHEN expect_time LIKE '%시간%' THEN "
					+ "      CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(expect_time, '시간', 1), ' ', -1) AS DECIMAL(5,2)) * 60 "
					+ "    WHEN expect_time LIKE '%분%' THEN "
					+ "      CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(expect_time, '분', 1), ' ', -1) AS DECIMAL(5,2)) "
					+ "    ELSE " + "      CAST(expect_time AS DECIMAL(5,2)) " + "  END DESC;";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getString(1));
				course.setLocation(rs.getString(2));
				course.setLength(rs.getString(3));
				course.setExpect_time(rs.getString(4));
				course.setDifficulty(rs.getString(5));
				course.setFile_id(rs.getString(6));
				courses.add(course);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	public ArrayList<Course> LoadCourseTimeAsc() {
		ArrayList<Course> courses = new ArrayList<>();

		try {
			String sql = "SELECT * FROM course " + "ORDER BY " + "  CASE "
					+ "    WHEN expect_time LIKE '%시간%' AND expect_time LIKE '%분%' THEN "
					+ "      CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(expect_time, '시간', 1), ' ', -1) AS DECIMAL(5,2)) * 60 + "
					+ "      CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(expect_time, '분', -1), ' ', -1) AS DECIMAL(5,2)) "
					+ "    WHEN expect_time LIKE '%시간%' THEN "
					+ "      CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(expect_time, '시간', 1), ' ', -1) AS DECIMAL(5,2)) * 60 "
					+ "    WHEN expect_time LIKE '%분%' THEN "
					+ "      CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(expect_time, '분', 1), ' ', -1) AS DECIMAL(5,2)) "
					+ "    ELSE " + "      CAST(expect_time AS DECIMAL(5,2)) " + "  END ASC;";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getString(1));
				course.setLocation(rs.getString(2));
				course.setLength(rs.getString(3));
				course.setExpect_time(rs.getString(4));
				course.setDifficulty(rs.getString(5));
				course.setFile_id(rs.getString(6));
				courses.add(course);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	public ArrayList<Course> LoadCourseDistanceDesc() {
		ArrayList<Course> courses = new ArrayList<>();

		try {
			String sql = "SELECT * FROM course ORDER BY CAST(SUBSTRING_INDEX(length, 'km', 1) AS DECIMAL(5,2)) DESC";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getString(1));
				course.setLocation(rs.getString(2));
				course.setLength(rs.getString(3));
				course.setExpect_time(rs.getString(4));
				course.setDifficulty(rs.getString(5));
				course.setFile_id(rs.getString(6));
				courses.add(course);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	public ArrayList<Course> LoadCourseDistanceAsc() {
		ArrayList<Course> courses = new ArrayList<>();

		try {
			String sql = "SELECT * FROM course ORDER BY CAST(SUBSTRING_INDEX(length, 'km', 1) AS DECIMAL(5,2)) ASC";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getString(1));
				course.setLocation(rs.getString(2));
				course.setLength(rs.getString(3));
				course.setExpect_time(rs.getString(4));
				course.setDifficulty(rs.getString(5));
				course.setFile_id(rs.getString(6));
				courses.add(course);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	public Course bringCourse(String id) {
		Course course = new Course();

		try {
			String sql = "select * from course where id =?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				course.setId(rs.getString(1));
				course.setLocation(rs.getString(2));
				course.setLength(rs.getString(3));
				course.setExpect_time(rs.getString(4));
				course.setDifficulty(rs.getString(5));
				course.setFile_id(rs.getString(6));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return course;
	}
}

package model;

import java.util.ArrayList;

import common.DBConnector;

public class MyCourseDAO extends DBConnector{
	
	public MyCourseDAO() {
		super();
	}
	
	public ArrayList<String> getCourses(String user_id){
		ArrayList<String> ids = new ArrayList<String>();
		
		try {
			String sql = "select courseId from mycourse where memId = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, user_id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String courseId = rs.getString("courseId");
			    ids.add(courseId);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ids;
	}
}

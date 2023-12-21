package model;

import common.DBConnector;

public class CourseRecommendDAO extends DBConnector{
	
	public CourseRecommendDAO() {
		super();
	}
	
	public int addRecommend(String user_id, String course_id) {
		int result = 0;
		
		try {
			String sql = "insert into courseRecommend values (?, ?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, user_id);
			psmt.setString(2, course_id);
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
		}
		
		return result;
	}
	
	public int getRecommend(String course_id) {
		int result = 0;
		
		try {
			String sql = "select count(*) from courseRecommend where course_id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, course_id);
			rs = psmt.executeQuery();
			
			if(rs.next())
				result = rs.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

}

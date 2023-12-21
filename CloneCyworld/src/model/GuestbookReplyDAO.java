package model;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnector;

public class GuestbookReplyDAO extends DBConnector{

	public int insert(GuestbookReply gbr) {
		int result = 0;
		
		try {
			String sql = "insert into guestbookReply (b_no,content,created,id) values (?,?,?,?)";
			psmt= con.prepareStatement(sql);
			psmt.setInt(1, gbr.getB_no());
			psmt.setString(2, gbr.getContent());
			psmt.setString(3, gbr.getCreated());
			psmt.setString(4, gbr.getId());
			result = psmt.executeUpdate();
			
			System.out.println("등록성공");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("등록실패");
		}
		
		return result;
	}
	
	public ArrayList<GuestbookReply> getreplyList(int no) {
	    ArrayList<GuestbookReply> list = new ArrayList<GuestbookReply>();

	    try {
	        String sql = "select * from guestbookReply where b_no = ?";
	        psmt = con.prepareStatement(sql);
	        psmt.setInt(1, no);
	        rs = psmt.executeQuery();
	        
	        while (rs.next()) {
	            GuestbookReply reply = new GuestbookReply();
	            reply.setR_no(rs.getInt(1));
	            reply.setB_no(rs.getInt(2));
	            reply.setContent(rs.getString(3));
	            reply.setCreated(rs.getString(4).substring(0, 16));
	            reply.setId(rs.getString(5));
	            // 결과셋을 이용하여 reply 객체를 초기화
	            // reply.setXXX(rs.getXXX());
	            list.add(reply);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // 오류 메시지 출력
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

	    return list;
	}
	
	public int deleteReply(int r_no) {
		int result = 0;
		
		try {
			String sql = "delete from guestbookReply where r_no = ?";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, r_no);
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	public int deleteReplyforbook(int no) {
		int result = 0;
		
		try {
			String sql = "delete from guestbookReply where b_no = ?";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, no);
			result = psmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	public int countReply(int no) {
		int result = 0;
		
		try {
			String sql = "select count(*) from guestbookReply where b_no =?";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			if(rs.next())
				result = rs.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	
}

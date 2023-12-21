package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import common.DBConnector;

public class GuestbookDAO extends DBConnector {
	public int InsertGuestbook(Guestbook guestbook, String id) {
		String INSERT_GUESTBOOK_SQL = "insert into guestbook (id, owner_id, created, content) values (?, ?, ?, ?);";
		
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		String formattedTime = currentTime.format(formatter);
		
		int result = 0;
		
		try {
			psmt = con.prepareStatement(INSERT_GUESTBOOK_SQL);
			psmt.setString(1, guestbook.getId());
			psmt.setString(2, guestbook.getOwner_id());
			psmt.setString(3, formattedTime);
			psmt.setString(4, guestbook.getContent());
			
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Guestbook> getList(String owner_id){
		ArrayList<Guestbook> list = new ArrayList<>();
		GuestbookReplyDAO dao = new GuestbookReplyDAO();
		
		try {
			String sql = "select guestbook.*, imgFile from guestbook join member on member.id = guestbook.id where owner_id = ? order by no desc;";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, owner_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Guestbook guestbook = new Guestbook();
				ArrayList<GuestbookReply> reply = new ArrayList<>();
				reply = dao.getreplyList(rs.getInt(1));
				
				guestbook.setNo(rs.getInt(1));
				guestbook.setId(rs.getString(2));
				guestbook.setOwner_id(rs.getString(3));
				guestbook.setCreated(rs.getString(4).substring(0, 16));
				guestbook.setContent(rs.getString(5));
				guestbook.setImgFile(rs.getString(6));
				guestbook.setReplyList(reply);
				list.add(guestbook);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		dao.close();
		
		return list;
	}
	
	public int deleteGuestbook(int no){
		int result = 0;
		
			try {
				String sql = "delete from guestbook where no =?";
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, no);
				result = psmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		return result;
	}
	
	public int todaycount(String owner_id) {
		int count = 0;
		
		try {
			String sql = "select count(*) from guestbook where owner_id = ? and created like ?";
			LocalDateTime currentTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedTime = currentTime.format(formatter);
			formattedTime = "%" + formattedTime + "%";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, owner_id);
			psmt.setString(2, formattedTime);
			rs = psmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return count;
	}
	

}

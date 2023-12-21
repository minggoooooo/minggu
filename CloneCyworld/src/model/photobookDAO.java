package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import common.DBConnector;

public class photobookDAO extends DBConnector{
	
	public photobookDAO() {
		super();
	}
	
	public int insertPhotobook(photobook photobook) {
		int result = 0;
		
		try {
			String sql = "insert into photobook (owner_id, title, created, content, imageName) values "
					+ "(?, ?, ?, ?, ?)";
			LocalDateTime currentTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
			String formattedTime = currentTime.format(formatter);
			psmt = con.prepareStatement(sql);
			psmt.setString(1, photobook.getOwner_id());
			psmt.setString(2, photobook.getTitle());
			psmt.setString(3, formattedTime);
			psmt.setString(4, photobook.getContent());
			psmt.setString(5, photobook.getImageName());
			result = psmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<photobook> readlist(String owner_id){
		ArrayList<photobook> list = new ArrayList<>();
		
		try {
			String sql ="select * from photobook where owner_id = ? order by no desc";
			psmt= con.prepareStatement(sql);
			psmt.setString(1, owner_id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				photobook book = new photobook();
				book.setNo(rs.getInt(1));
				book.setOwner_id(rs.getString(2));
				book.setTitle(rs.getString(3));
				book.setCreated(rs.getString(4).substring(0, 16));
				book.setContent(rs.getString(5));
				book.setImageName(rs.getString(6));
				list.add(book);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int todaycount(String owner_id) {
		int count = 0;
		
		try {
			String sql = "select count(*) from photobook where owner_id = ? and created like ?";
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

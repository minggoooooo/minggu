package model;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnector;

public class jukeboxDAO extends DBConnector{
	
	public jukeboxDAO() {
		super();
	}
	
	public ArrayList<jukebox> getMusicList(String owner_id){
		ArrayList<jukebox> list  = new ArrayList<>();
		
		try {
			String sql = "select * from jukebox where owner_id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, owner_id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				jukebox juke = new jukebox();
				juke.setNo(rs.getInt(1));
				juke.setTitle(rs.getString(2));
				juke.setArtist(rs.getString(3));
				juke.setId(rs.getString(4));
				juke.setPicked(rs.getBoolean(5));
				juke.setOwner_id(rs.getString(6));
				list.add(juke);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<jukebox> getPlayList(String owner_id){
		ArrayList<jukebox> list  = new ArrayList<>();
		
		try {
			String sql = "select * from jukebox where owner_id = ? and picked = true";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, owner_id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				jukebox juke = new jukebox();
				juke.setNo(rs.getInt(1));
				juke.setTitle(rs.getString(2));
				juke.setArtist(rs.getString(3));
				juke.setId(rs.getString(4));
				juke.setPicked(rs.getBoolean(5));
				juke.setOwner_id(rs.getString(6));
				list.add(juke);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int addMusic(jukebox music) {
		int result = 0;
		
		try {
			String sql = "insert into jukebox (title, artist, id, owner_id) values (?,?,?,?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, music.getTitle());
			psmt.setString(2, music.getArtist());
			psmt.setString(3, music.getId());
			psmt.setString(4, music.getOwner_id());
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	public int setBoolean(int no) {
		int result = 0;
		try {
			String sql = "update jukebox set picked = true where no =?";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, no);
			result = psmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int reset() {
		int result = 0;
		try {
			String sql ="update jukebox set picked = false";
			stmt = con.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
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

}

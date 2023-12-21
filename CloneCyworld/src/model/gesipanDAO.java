package model;

import java.util.ArrayList;

import common.DBConnector;

public class gesipanDAO extends DBConnector{

	public gesipanDAO() {
		super();
	}
	
	public int countGesi(String owner_id) {
		int num =0;
		
		try {
			String sql = "select * count from guestbook where owner_id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, owner_id);
			rs = psmt.executeQuery();
			
			num = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return num;
	}
	
	public ArrayList<gesipan> readgesipan(String owner_id) {
		ArrayList<gesipan> list = new ArrayList<>();
		
		try {
			String sql = "select * from guestbook where owner_id = ? ";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, owner_id);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				gesipan gesipan = new gesipan();
				gesipan.setNo(rs.getInt(1));
				gesipan.setId(rs.getString(2));
				gesipan.setOwner_id(rs.getString(3));
				gesipan.setDate(rs.getDate(4));
				gesipan.setContent(rs.getString(5));
				list.add(gesipan);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
}

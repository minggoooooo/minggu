package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBConnector;

public class CrewScheduleMemberDAO extends DBConnector {

	// 단기크루 멤버로 등록하기 or 장기크루 일정등록하기
	public void joinCrew(CrewScheduleMemberDTO dto) {
		int result = 0;
		String JOIN_CREW_SQL = "insert into crewSchedule values(?, ?, ?, ?, ?)";
		
		try {
			psmt = con.prepareStatement(JOIN_CREW_SQL);
			psmt.setInt(1, dto.getNo());
			psmt.setString(2, dto.getCrewName());
			psmt.setInt(3, dto.getMemberNum());
			psmt.setString(4, dto.getMemId());
			psmt.setBoolean(5, dto.isMemAdmin());
			result = psmt.executeUpdate();
			
			System.out.println("joinCrew 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("joinCrew 실패");
		}
	}
		
	
	
	// 장기크루 일정에 멤버 등록하기(단기크루에 멤버 추가하기)
	public int joinSchedule(CrewScheduleMemberDTO dto) {
		int result = 0;
		String JOIN_SCHEDULE = "insert into crewSchedule values(?,?,?,?,?)";
		
		try {
			psmt = con.prepareStatement(JOIN_SCHEDULE);
			psmt.setInt(1, dto.getNo());
			psmt.setString(2, dto.getCrewName());
			psmt.setInt(3, dto.getMemberNum());
			psmt.setString(4, dto.getMemId());
			psmt.setBoolean(5, dto.isMemAdmin());
			result = psmt.executeUpdate();
			System.out.println("joinSchedule 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("joinSchedule 실패");
		}
		
		return result;
	}
	
	

	// 크루명으로 단기크루 넘버 가져오기(장기크루면 일정넘버 가져오기)
	public int selectCrewNum(String crewName) {
		int result = 0;
		String SELECT_CREWNUM = "select * from crewSchedule where crewName = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_CREWNUM);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			System.out.println("selectCrewNum 성공");
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectCrewNum 실패");
		}
		
		return result;
	}
	
	
	
	
}

package model;

import common.DBConnector;

public class CrewDAO extends DBConnector {

	// 단기크루 멤버로 등록하기
	public void joinCrew(CrewDTO dto) {
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
	
	
}
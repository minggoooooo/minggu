package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.DBConnector;

public class LongCrewDAO extends DBConnector {

	// 장기크루 만들기
	public void makeLongCrew(LongCrewRecruitDTO dto) {
		int result = 0;
		String MAKE_LONGCREW_SQL = "insert into longCrewRecruit values(?,?,?,?,?,?)";

		try {
			psmt = con.prepareStatement(MAKE_LONGCREW_SQL);
			psmt.setString(1, dto.getCrewName());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getMemberNum());
			psmt.setString(4, dto.getCreated());
			psmt.setString(5, dto.getAdminId());
			psmt.setString(6, dto.getDueDate());
			result = psmt.executeUpdate();
			
			System.out.println("makeLongCrew 성공");
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("makeLongCrew 실패");
		}
		
	}
	
	
	// 모든 장기크루 리스트 불러오기
	public List<LongCrewRecruitDTO> selectAllRecruit() {
		List<LongCrewRecruitDTO> crewList = new ArrayList<LongCrewRecruitDTO>();
		String SELECT_ALL_LONGCREW = "select *, (select count(memId) from longCrewMember where crewName = longCrewRecruit.crewName) as totalCount "
				+ "from longCrewRecruit order by created desc";

		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_LONGCREW);
			
			while(rs.next()) {
				LongCrewRecruitDTO dto = new LongCrewRecruitDTO();
				dto.setCrewName(rs.getString(1));
				dto.setContent(rs.getString(2));
				dto.setMemberNum(rs.getInt(3));
				dto.setCreated(rs.getString(4));
				dto.setAdminId(rs.getString(5));
				dto.setTotalCount(rs.getInt("totalCount"));
				dto.setDueDate(rs.getString("dueDate"));
				crewList.add(dto);
			}
			
			System.out.println("selectAllRecruit 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectAllRecruit 실패");
		}
		
		return crewList;
	}
	
	
	// 모집중인 장기크루 리스트 불러오기
	public List<LongCrewRecruitDTO> selectNowRecruit() {
		List<LongCrewRecruitDTO> crewList = new ArrayList<LongCrewRecruitDTO>();
		String SELECT_ALL_LONGCREW = "select *, (select count(memId) from longCrewMember where crewName = longCrewRecruit.crewName) as totalCount "
				+ "from longCrewRecruit order by dueDate desc";
		
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_LONGCREW);
			
			while(rs.next()) {
				LongCrewRecruitDTO dto = new LongCrewRecruitDTO();
				dto.setCrewName(rs.getString(1));
				dto.setContent(rs.getString(2));
				dto.setMemberNum(rs.getInt(3));
				dto.setCreated(rs.getString(4));
				dto.setAdminId(rs.getString(5));
				String dueDate = rs.getString("dueDate");
				dto.setDueDate(dueDate);
				dto.setTotalCount(rs.getInt("totalCount"));
				
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				
				// 크루 모임일자(Date로 변환해서 오늘 날짜와 비교)
				Date dueDate2 = formatter.parse(dueDate);
				Date now = new Date();
				String today = formatter.format(now);
				Date today2 = formatter.parse(today);
				
				int result = dueDate2.compareTo(today2);
				
				// 모일일자가 아직 지나지 않았으면 list에 넣음
				if(result > 0) {
					crewList.add(dto);
				}
				
				
				
			}
			
			System.out.println("selectNowRecruit 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectNowRecruit 실패");
		}
		
		return crewList;
	}

	
	
	// 모집중인 장기크루 리스트 불러오기
	public List<LongCrewRecruitDTO> selectDoneRecruit() {
		List<LongCrewRecruitDTO> crewList = new ArrayList<LongCrewRecruitDTO>();
		String SELECT_ALL_LONGCREW = "select *, (select count(memId) from longCrewMember where crewName = longCrewRecruit.crewName) as totalCount "
				+ "from longCrewRecruit order by dueDate desc";
		
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_LONGCREW);
			
			while(rs.next()) {
				LongCrewRecruitDTO dto = new LongCrewRecruitDTO();
				dto.setCrewName(rs.getString(1));
				dto.setContent(rs.getString(2));
				dto.setMemberNum(rs.getInt(3));
				dto.setCreated(rs.getString(4));
				dto.setAdminId(rs.getString(5));
				String dueDate = rs.getString("dueDate");
				dto.setDueDate(dueDate);
				dto.setTotalCount(rs.getInt("totalCount"));
				
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				
				// 크루 모임일자(Date로 변환해서 오늘 날짜와 비교)
				Date dueDate2 = formatter.parse(dueDate);
				Date now = new Date();
				String today = formatter.format(now);
				Date today2 = formatter.parse(today);
				
				int result = dueDate2.compareTo(today2);
				
				// 모일일자가 아직 지나지 않았으면 list에 넣음
				if(result < 0) {
					crewList.add(dto);
				}
				
				
				
			}
			
			System.out.println("selectDoneRecruit 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectDoneRecruit 실패");
		}
		
		return crewList;
	}
	
	
	// 특정 장기크루 모집글 상세정보 가져오기
	public LongCrewRecruitDTO selectRecruitDetail(String crewName) {
		LongCrewRecruitDTO dto = new LongCrewRecruitDTO();
		String SELECT_RECRUIT_DETAIL = "select *, (select count(memId) from longCrewMember where crewName = longCrewRecruit.crewName)"
				+ " as totalCount from longCrewRecruit where crewName = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_RECRUIT_DETAIL);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setCrewName(rs.getString(1));
				dto.setContent(rs.getString(2));
				dto.setMemberNum(rs.getInt(3));
				dto.setCreated(rs.getString(4));
				dto.setAdminId(rs.getString(5));
				dto.setTotalCount(rs.getInt("totalCount"));
				dto.setDueDate(rs.getString("dueDate"));
			}
			
			System.out.println("selectRecruitDetail 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectRecruitDetail 실패");
		}
		
		return dto;
	}
	
	
	// 장기크루 멤버로 등록하기
	public void joinLongCrew(LongCrewMemberDTO dto) {
		int result = 0;
		String JOIN_LONG_CREW = "insert into longCrewMember values(?,?,?,?,?)";
		
		try {
			psmt = con.prepareStatement(JOIN_LONG_CREW);
			psmt.setString(1, dto.getCrewName());
			psmt.setString(2, dto.getMemId());
			psmt.setInt(3, dto.getMemberNum());
			psmt.setBoolean(4, dto.isAdmin());
			psmt.setString(5, dto.getJoinDate());
			result = psmt.executeUpdate();
			
			System.out.println("joinLongCrew 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("joinLongCrew 실패");
		}
	}
	
	
	// 장기크루 일정 등록하기
	public void regiterSchedule(CrewRecruitDTO dto) {
		int result = 0;
		String REGISTER_SCHEDULE = "insert into crewRecruit values(null,?,?,?,?,?,?,?,?,false,null,null)";
		
		try {
			psmt = con.prepareStatement(REGISTER_SCHEDULE);
			psmt.setString(1, dto.getCrewName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getLocation());
			psmt.setInt(5, dto.getMemberNum());
			psmt.setString(6, dto.getCreated());
			psmt.setString(7, dto.getGatherDate());
			psmt.setString(8, dto.getAdminId());
			result = psmt.executeUpdate();
			
			System.out.println("장기크루 regiterSchedule 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("장기크루 regiterSchedule 실패");
		}
	}
	
	
	// 장기 크루 모든 크루이름 가져오기
	public List<LongCrewRecruitDTO> selectLongCrewName() {
		List<LongCrewRecruitDTO> crewNames = new ArrayList<LongCrewRecruitDTO>();
		String SELECT_CREWNAMES = "select crewName from longCrewRecruit"; 
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_CREWNAMES);
			
			while(rs.next()) {
				LongCrewRecruitDTO dto = new LongCrewRecruitDTO();
				dto.setCrewName(rs.getString(1));
				crewNames.add(dto);
			}
			
			System.out.println("selectLongCrewName 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectLongCrewName 실패");
		}
		
		return crewNames;
	}
	
	
	// 특정 크루 방장 id 가져오기(장기 크루)
	public String selectAdminId(String crewName) {
		String adminId = "";
		String SELECT_ADMINID = "select adminId from longCrewRecruit where crewName = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_ADMINID);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			if(rs.next()) {
				adminId = rs.getString(1);
			}
			System.out.println("selectAdminId 성공");
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectAdminId 실패");
		}
		return adminId;
	}
	
	// 특정 장기크루의 모든 memId 가져오기
	public List<LongCrewMemberDTO> selectMemid(String crewName) {
		List<LongCrewMemberDTO> memberList = new ArrayList<LongCrewMemberDTO>();
		String SELECT_MEMID = "select memId from longCrewMember where crewName = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_MEMID);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				LongCrewMemberDTO dto = new LongCrewMemberDTO();
				dto.setMemId(rs.getString(1));
				memberList.add(dto);
			}
			
			System.out.println("selectMemid 성공");

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectMemid 실패");
		}
		
		
		return memberList;
	}
	
	
}

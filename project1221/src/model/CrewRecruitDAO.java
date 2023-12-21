package model;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import common.DBConnector;

public class CrewRecruitDAO extends DBConnector {

	// 단기크루만들기
	public void makeCrew(CrewRecruitDTO dto) {
		int result = 0;
		String MAKE_CREW_SQL = "insert into crewRecruit values(null,?,?,?,?,?,?,?,?,true,?,null)";

		try {
			psmt = con.prepareStatement(MAKE_CREW_SQL);
			psmt.setString(1, dto.getCrewName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getLocation());
			psmt.setInt(5, dto.getMemberNum());
			psmt.setString(6, dto.getCreated());
			psmt.setString(7, dto.getGatherDate());
			psmt.setString(8, dto.getAdminId());
			psmt.setString(9, dto.getCourseId());
			result = psmt.executeUpdate();
			
			System.out.println("makeCrew 성공");
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("makeCrew 실패");
		}
		
	}
	
	// 단기크루 모집글 모두 불러오기(+현재 모집인원)
	public List<CrewRecruitDTO> selectAllRecruit() {
		List<CrewRecruitDTO> crewList = new ArrayList<CrewRecruitDTO>();
		String SELECT_ALLRECRUIT_SQL = "select *, (select count(memId) from crewSchedule where crewName = crewRecruit.crewName)"
				+ " as totalCount from crewRecruit where isCrew = true order by created desc";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_ALLRECRUIT_SQL);
			
			while(rs.next()) {
				CrewRecruitDTO dto = new CrewRecruitDTO();
				dto.setNo(rs.getInt(1));
				dto.setCrewName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setLocation(rs.getString(5));
				dto.setMemberNum(rs.getInt(6));
				dto.setCreated(rs.getString(7));
				dto.setGatherDate(rs.getString(8));
				dto.setAdminId(rs.getString(9));
				dto.setCrew(rs.getBoolean(10));
				dto.setCourseId(rs.getString(11));
				dto.setTotalCount(rs.getInt(12));
				crewList.add(dto);
			}
			
			System.out.println("selectAllRecruit 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectAllRecruit 실패");
		}
		return crewList;
	}
	
	
	// 활동중인 단기크루 모집 리스트 가져오기
	public List<CrewRecruitDTO> selectNowRecruit() {
		List<CrewRecruitDTO> crewList = new ArrayList<CrewRecruitDTO>();
		String SELECT_NOW_RECRUIT = "select *, (select count(memId) from crewSchedule where crewName = crewRecruit.crewName)"
				+ " as totalCount from crewRecruit where isCrew = true order by created desc";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_NOW_RECRUIT);
			
			while(rs.next()) {
				CrewRecruitDTO dto = new CrewRecruitDTO();
				dto.setNo(rs.getInt("no"));
				dto.setCrewName(rs.getString("crewName"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setLocation(rs.getString("location"));
				dto.setMemberNum(rs.getInt("memberNum"));
				dto.setCreated(rs.getString("created"));
				String gatherDate = rs.getString("gatherDate");
				dto.setGatherDate(gatherDate);
				dto.setAdminId(rs.getString("adminId"));
				dto.setCrew(rs.getBoolean("isCrew"));
				dto.setCourseId(rs.getString("courseId"));
				dto.setTotalCount(rs.getInt("totalCount"));
				
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				
				// 크루 모임일자(Date로 변환해서 오늘 날짜와 비교)
				Date gatherDate2 = formatter.parse(gatherDate);
				Date now = new Date();
				String today = formatter.format(now);
				Date today2 = formatter.parse(today);
				
				int result = gatherDate2.compareTo(today2);
				
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
	
	
	// 활동중인 단기크루 모집 리스트 가져오기
	public List<CrewRecruitDTO> selectDoneRecruit() {
		List<CrewRecruitDTO> crewList = new ArrayList<CrewRecruitDTO>();
		String SELECT_NOW_RECRUIT = "select *, (select count(memId) from crewSchedule where crewName = crewRecruit.crewName)"
				+ " as totalCount from crewRecruit where isCrew = true order by gatherDate desc";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_NOW_RECRUIT);
			
			while(rs.next()) {
				CrewRecruitDTO dto = new CrewRecruitDTO();
				dto.setNo(rs.getInt("no"));
				dto.setCrewName(rs.getString("crewName"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setLocation(rs.getString("location"));
				dto.setMemberNum(rs.getInt("memberNum"));
				dto.setCreated(rs.getString("created"));
				String gatherDate = rs.getString("gatherDate");
				dto.setGatherDate(gatherDate);
				dto.setAdminId(rs.getString("adminId"));
				dto.setCrew(rs.getBoolean("isCrew"));
				dto.setCourseId(rs.getString("courseId"));
				dto.setTotalCount(rs.getInt("totalCount"));
				
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				
				// 크루 모임일자(Date로 변환해서 오늘 날짜와 비교)
				Date gatherDate2 = formatter.parse(gatherDate);
				Date now = new Date();
				String today = formatter.format(now);
				Date today2 = formatter.parse(today);
				
				int result = gatherDate2.compareTo(today2);
				
				// 모일일자가 지났으면 list에 넣음(활동종료된 크루이기 때문)
				if(result <= 0) {
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
		
	
	
	
	
	
	
	// 특정 크루 상세보기 불러오기
	public CrewRecruitDTO selectRecruitDetail(String crewName) {
		CrewRecruitDTO dto = new CrewRecruitDTO();
		String SELECT_RECRUIT_DETAIL = "select *, (select count(memId) from crewSchedule where crewName = crewRecruit.crewName)"
				+ " as totalCount from crewRecruit where crewName = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_RECRUIT_DETAIL);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt(1));
				dto.setCrewName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setLocation(rs.getString(5));
				dto.setMemberNum(rs.getInt(6));
				dto.setCreated(rs.getString(7));
				dto.setGatherDate(rs.getString(8));
				dto.setAdminId(rs.getString(9));
				dto.setCrew(rs.getBoolean(10));
				dto.setCourseId(rs.getString(11));
				dto.setTotalCount(rs.getInt(12));
			}
			
			System.out.println("selectRecruitDetail 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectRecruitDetail 실패");
		}
		return dto;
	}
	
	// 크루명 중복이름 확인
	public int checkCrewName(String crewName) {
		int result = 0;
		String CHECK_CREWNAME_SQL = "select crewName from crewRecruit where crewName = ?";
		
		try {
			psmt = con.prepareStatement(CHECK_CREWNAME_SQL);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
			
			System.out.println("checkCrewName 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("checkCrewName 실패");
		}
		
		return result;
	}
	
	// 기존 크루명 모두 가져오기
	public List<CrewRecruitDTO> selectCrewName() {
		List<CrewRecruitDTO> cNameList = new ArrayList<CrewRecruitDTO>();
		String SELECT_CREWNAME = "select crewName from crewRecruit where isCrew = true";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_CREWNAME);
			 
			while(rs.next()) {
				CrewRecruitDTO dto = new CrewRecruitDTO();
				dto.setCrewName(rs.getString("crewName"));
				cNameList.add(dto);
			}
			
			System.out.println("selectCrewName 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectCrewName 실패");
		}
		
		return cNameList;
	}
	
	// 방장 제외 인원 0명이면서 한달 지난 크루 삭제(장기크루에서 사용)
	public void deleteCrew(String crewName, String created) {
		int result = 0;
		String DELETE_CREW_SQL = "delete from longCrewRecruit where crewName = ? and (select count(*) from longCrewMember where crewName = ?) <= 1;";
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			  
			
			// 크루 생성 날짜(Calendar로 변환)
			Date date = formatter.parse(created);
			Calendar created2 = Calendar.getInstance();
			created2.setTime(date);
			
			// 한달 전 날짜
			Calendar monthAgo = Calendar.getInstance();
			monthAgo.add(Calendar.MONTH , -1);    
		
			// 두 날짜 간 차이 계산
			boolean monthsBetween = created2.before(monthAgo);
			
			// 한달 이상이면 쿼리문 실행해서 삭제
			if(monthsBetween == true) {
				psmt = con.prepareStatement(DELETE_CREW_SQL);
				psmt.setString(1, crewName);
				psmt.setString(2, crewName);
				result = psmt.executeUpdate();
				
			}
			
			System.out.println("deleteCrew 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("deleteCrew 실패");
		}
		
	}
	
	// 모집인원이 방장포함 1명이하이면서 모집일이 지나면 크루 삭제(단기 크루에서 사용)
	public void deleteCrew2(String crewName, String gatherDate) {
		String DELETE_CREW_SQL = "delete from crewRecruit where crewName = ? and (select count(*) from crewSchedule where crewName = ?) <= 1 and isCrew = true";
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			  
			
			// 크루 모집일 날짜
			Date gatherDate2 = formatter.parse(gatherDate);
			
			// 오늘 날짜
			Date now = new Date();
			String today = formatter.format(now);
			Date today2 = formatter.parse(today);
		
			// 모집일이 지났는지 확인
			int result = gatherDate2.compareTo(today2);
			System.out.println(result);
			// 오늘 날짜 지났으면 삭제
			if(result < 0) {
				psmt = con.prepareStatement(DELETE_CREW_SQL);
				psmt.setString(1, crewName);
				psmt.setString(2, crewName);
				result = psmt.executeUpdate();
			}
			
			System.out.println("deleteCrew 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("deleteCrew 실패");
		}
		
	}
	
	
	
	
	// 특정 id에 예정된 단기크루 스케쥴 모두 가져오기
	public List<CrewRecruitDTO> selectPlannedSchedule(String id) {
		List<CrewRecruitDTO> list = new ArrayList<CrewRecruitDTO>();
		String PLANNED_SCHEDULE_LIST = "select crewRecruit.*, (select count(memId) from crewSchedule where crewSchedule.no = crewRecruit.no) totalCount "
				+ "from crewRecruit join crewSchedule on crewSchedule.no = crewRecruit.no where crewSchedule.memId = ?";
		
		try {
			
			psmt = con.prepareStatement(PLANNED_SCHEDULE_LIST);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
				
				while(rs.next()) {
					CrewRecruitDTO dto = new CrewRecruitDTO();
					dto.setNo(rs.getInt(1));
					dto.setCrewName(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setLocation(rs.getString(5));
					dto.setMemberNum(rs.getInt(6));
					dto.setCreated(rs.getString(7));
					dto.setGatherDate(rs.getString(8));
					dto.setAdminId(rs.getString(9));
					dto.setCrew(rs.getBoolean(10));
					dto.setCourseId(rs.getString(11));
					dto.setTotalCount(rs.getInt("totalCount"));
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

					// 크루 생성 날짜(Date로 변환)
					Date gatherDate = formatter.parse(rs.getString("gatherDate"));
					
					// 오늘 날짜
					Date now = new Date();
					String today = formatter.format(now);
					Date today2 = formatter.parse(today);
					
					// 오늘 날짜 이전인지 확인
					int result = gatherDate.compareTo(today2);
					
					
					if(result > 0) {
						list.add(dto);
				}
		}	
			System.out.println("selectPlannedSchedule 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectPlannedSchedule 실패");
		}
		
		return list;
	}

	
	// 특정 crewName의 예정된 스케쥴 모두 가져오기
	public List<CrewRecruitDTO> selectPlannedSchedule2(String crewName, String memId) {
		List<CrewRecruitDTO> list = new ArrayList<CrewRecruitDTO>();
		String PLANNED_SCHEDULE_LIST2 = "select *, (select count(memId) from crewSchedule where crewSchedule.no = crewRecruit.no) as totalCount "
				+ "from crewRecruit where crewName = ? and isCrew = 0";
		
		try {
			
			psmt = con.prepareStatement(PLANNED_SCHEDULE_LIST2);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				CrewRecruitDTO dto = new CrewRecruitDTO();
				int no = rs.getInt("no");
				dto.setNo(no);
				String crewName2 = rs.getString("crewName");
				dto.setCrewName(crewName2);
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setLocation(rs.getString(5));
				dto.setMemberNum(rs.getInt(6));
				dto.setCreated(rs.getString(7));
				String gather = rs.getString("gatherDate");
				System.out.println(gather);
				dto.setGatherDate(gather);
				dto.setAdminId(rs.getString(9));
				boolean isCrew = rs.getBoolean("isCrew");
				dto.setCrew(isCrew);
				dto.setCourseId(rs.getString("courseId"));
				dto.setTotalCount(rs.getInt("totalCount"));
				
				// 유저가 일정테이블에 이미 들어가있는지 확인하고 불린값 받아서 예정된일정 페이지에서 일정참여 버튼 출력
				// 일정참여완료 버튼 눌러서 일정테이블에 들어가있으면 true, 아니면 false 반환
				boolean check = memberCheck(no, memId);
				
				// 유저가 특정 크루에 포함되어있는지 확인하고 불린값 받아서 예정된일정 페이지에서 일정참여 버튼 출력
				boolean memCheck = true;
				if(isCrew == true) {		// 단기크루면 단기크루에 멤버 이름 있는지 확인
					memCheck = memberCheckInCrew(crewName2, memId);
				}							// 장기크루면 장기크루에 멤버 이름 있는지 확인
					memCheck = memberCheckInCrew2(crewName2, memId);
					
				// 유저가 크루의 멤버인데 일정에 속해있지 않으면 일정참여 버튼 보이게 boolean 값 true로 반환
				if(check==false && memCheck==true) {
					dto.setMember(true);
				} else {
					dto.setMember(false);
				}
					
				
				// 크루 생성 날짜(Date로 변환)
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date gatherDate = formatter.parse(gather);
				
				// 오늘 날짜
				Date now = new Date();
				String today = formatter.format(now);
				Date today2 = formatter.parse(today);
				
				// 오늘 날짜 이전인지 확인
				int result = gatherDate.compareTo(today2);
				
				System.out.println(result);
				
				if(result > 0) {
					list.add(dto);
				}
			}	
			System.out.println("selectPlannedSchedule2 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectPlannedSchedule2 실패");
		}
		
		return list;
	}
	
	
	// 일정에 있는 모든 멤버 가져오기
	/*
	 * public List<CrewScheduleMemberDTO> selectScheduleMembers(int no) {
	 * List<CrewScheduleMemberDTO> memberList = new
	 * ArrayList<CrewScheduleMemberDTO>(); String SELECT_SCHEDULE_MEMBERS =
	 * "select memId from crewSchedule where no = ?";
	 * 
	 * try { psmt = con.prepareStatement(SELECT_SCHEDULE_MEMBERS); psmt.setInt(1,
	 * no); ResultSet rs2 = psmt.executeQuery();
	 * 
	 * while(rs2.next()) { CrewScheduleMemberDTO dto = new CrewScheduleMemberDTO();
	 * dto.setMemId(rs.getString(1)); memberList.add(dto); }
	 * 
	 * System.out.println("selectScheduleMembers 성공");
	 * 
	 * } catch(Exception e) { e.printStackTrace();
	 * System.out.println("selectScheduleMembers 실패"); }
	 * 
	 * 
	 * return memberList; }
	 */
	
	// 특정 id의 마감된 크루 스케쥴 모두 가져오기
	public List<CrewRecruitDTO> selectClosedSchedule(String id) {
	List<CrewRecruitDTO> list = new ArrayList<CrewRecruitDTO>();
	String CLOSED_SCHEDULE_LIST = "select crewRecruit.*, (select count(memId) from crewSchedule where crewSchedule.no = crewRecruit.no) totalCount "
			+ "from crewRecruit join crewSchedule on crewSchedule.no = crewRecruit.no where crewSchedule.memId = ?";
			
	
			try {
				
				psmt = con.prepareStatement(CLOSED_SCHEDULE_LIST);
				psmt.setString(1, id);
				rs = psmt.executeQuery();
				
					
					while(rs.next()) {
						CrewRecruitDTO dto = new CrewRecruitDTO();
						dto.setNo(rs.getInt(1));
						dto.setCrewName(rs.getString(2));
						dto.setTitle(rs.getString(3));
						dto.setContent(rs.getString(4));
						dto.setLocation(rs.getString(5));
						dto.setMemberNum(rs.getInt(6));
						dto.setCreated(rs.getString(7));
						dto.setGatherDate(rs.getString(8));
						dto.setAdminId(rs.getString(9));
						dto.setCrew(rs.getBoolean(10));
						dto.setCourseId(rs.getString(11));
						dto.setTotalCount(rs.getInt("totalCount"));
						
						boolean complete = completeCheck(id, rs.getInt("no"));
						dto.setComplete(complete);
						
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

						// 크루 생성 날짜(Date로 변환)
						Date gatherDate = formatter.parse(rs.getString("gatherDate"));
						
						// 오늘 날짜
						Date now = new Date();
						String today = formatter.format(now);
						Date today2 = formatter.parse(today);
						
						// 오늘 날짜 이전인지 확인
						int result = gatherDate.compareTo(today2);
						
						
						if(result <= 0) {
							list.add(dto);
					}
			}	
				System.out.println("selectClosedSchedule 성공");
			
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("selectClosedSchedule 실패");
			}
			
			return list;
		}
	
		// 특정 일정(no)에 memId가 있는지 확인
		public boolean memberCheck(int no, String memId) {
			boolean check = false;			// 일정 멤버 테이블에 멤버가 없으면 false 반환
			String CHECK_MEMBER_IN_SCHEDULE = "select * from crewSchedule where no = ? and memId = ?";
			
			try {
				psmt = con.prepareStatement(CHECK_MEMBER_IN_SCHEDULE);
				psmt.setInt(1, no);
				psmt.setString(2, memId);
				ResultSet rs2 = psmt.executeQuery();
				
				if(rs2.next()) {
					check = true;			// 일정 멤버 테이블에 이미 멤버가 있으면 true 반환
				}
				rs2.close();
				System.out.println("memberCheck 성공");
				
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("memberCheck 실패");
			}
			return check;
		}
		
		// 특정 단기크루에 멤버가 있는지 확인
		public boolean memberCheckInCrew(String crewName, String memId) {
			boolean check = false;		// 단기 크루에 멤버 이름 없으면 false;
			String MEMBER_CHECK_INCREW = "select * from crewSchedule where crewName = ? and memId = ?";
			
			try {
				psmt = con.prepareStatement(MEMBER_CHECK_INCREW);
				psmt.setString(1, crewName);
				psmt.setString(2, memId);
				ResultSet rs3 = psmt.executeQuery();
				
				if(rs3.next()) {	// 단기 크루에 멤버 이름 있으면 true;
					check = true;
				}
				
				System.out.println("memberCheckInCrew 성공");
				
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("memberCheckInCrew 싫패");
			}
			
			return check;
		}
		
		// 특정 단기크루에 멤버가 있는지 확인
		public boolean memberCheckInCrew2(String crewName, String memId) {
			boolean check = false;			// 장기크루에 멤버 이름 없으면 false 반환
			String MEMBER_CHECK_INCREW2 = "select * from longCrewMember where crewName = ? and memId = ?";

			try {
				psmt = con.prepareStatement(MEMBER_CHECK_INCREW2);
				psmt.setString(1, crewName);
				psmt.setString(2, memId);
				ResultSet rs4 = psmt.executeQuery();
				
				if(rs4.next()) {			// 장기크루에 멤버 이름 있으면 true 반환
					check = true;		
				}
				
				System.out.println("memberCheckInCrew2 성공");

			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("memberCheckInCrew2 실패");
			}
			return check;
		}
	
		
		  // 특정 id와 일정 no가 course테이블에 있는지 확인 
		  public boolean completeCheck(String memId, int no) { 
			  boolean complete = true; // 테이블에 없으면 true 반환 
			  String COMPLETE_CHECK = "select * from course where memId=? and no=?";
		  
			  try {
				  psmt = con.prepareStatement(COMPLETE_CHECK);
				  psmt.setString(1, memId);
				  psmt.setInt(2, no); 
				  ResultSet rs2= psmt.executeQuery(); 
				  
				  if(rs2.next()) { //일치하는 아이디와 일정넘버가 있으면 false 반환 
					  complete = false; 
				} 
				  rs2.close();
			  
			  	  System.out.println("completeCheck 성공");
			  
			  } catch(Exception e) { 
				  e.printStackTrace();
				  System.out.println("completeCheck 실패");
			  }
			  
			  
			  return complete;
		 }
		 
	
	
	// 특정 crewName의 마감된 크루 스케쥴 모두 가져오기(단기크루)
	public List<CrewRecruitDTO> selectClosedSchedule2(String crewName) {
		List<CrewRecruitDTO> list = new ArrayList<CrewRecruitDTO>();
		String CLOSED_SCHEDULE_LIST = "select crewRecruit.*, (select count(memId) from crewSchedule where no = crewRecruit.no) as totalCount "
				+ "from crewRecruit where crewRecruit.crewName = ?";
		
		
		try {
			
			psmt = con.prepareStatement(CLOSED_SCHEDULE_LIST);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				CrewRecruitDTO dto = new CrewRecruitDTO();
				dto.setNo(rs.getInt(1));
				dto.setCrewName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setLocation(rs.getString(5));
				dto.setMemberNum(rs.getInt(6));
				dto.setCreated(rs.getString(7));
				dto.setGatherDate(rs.getString(8));
				dto.setAdminId(rs.getString(9));
				dto.setCrew(rs.getBoolean(10));
				dto.setCourseId(rs.getString(11));
				dto.setTotalCount(rs.getInt("totalCount"));
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				
				// 크루 생성 날짜(Date로 변환)
				Date gatherDate = formatter.parse(rs.getString("gatherDate"));
				
				// 오늘 날짜
				Date now = new Date();
				String today = formatter.format(now);
				Date today2 = formatter.parse(today);
				
				// 오늘 날짜 이전인지 확인
				int result = gatherDate.compareTo(today2);
				
				
				if(result <= 0) {
					list.add(dto);
				}
			}	
			System.out.println("selectClosedSchedule 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectClosedSchedule 실패");
		}
		
		return list;
	}
	
	
	// 특정 id가 참여중인 모든 단기 크루 불러오기
	public List<CrewRecruitDTO> selectCrewById(String memId) {
		List<CrewRecruitDTO> crewList = new ArrayList<CrewRecruitDTO>();
		String SELECT_CREW_BY_ID = "select crewRecruit.*, (select count(memId) from crewSchedule where crewName = crewRecruit.crewName) as totalCount from crewRecruit "
				+ "join crewSchedule on crewSchedule.crewName = crewRecruit.crewName where crewSchedule.memId = ? and isCrew = true"; 
		
		try {
			psmt = con.prepareStatement(SELECT_CREW_BY_ID);
			psmt.setString(1, memId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CrewRecruitDTO dto = new CrewRecruitDTO();
				dto.setNo(rs.getInt("no"));
				dto.setCrewName(rs.getString("crewName"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setLocation(rs.getString("location"));
				dto.setMemberNum(rs.getInt("memberNum"));
				dto.setCreated(rs.getString("created"));
				dto.setGatherDate(rs.getString("gatherDate"));
				dto.setAdminId(rs.getString("adminId"));
				dto.setCrew(rs.getBoolean("isCrew"));
				dto.setTotalCount(rs.getInt("totalCount"));
				crewList.add(dto);
			}
			
			System.out.println("selectCrewById 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectCrewById 실패");
		}
		
		return crewList;
	}

	
	// 특정 id가 참여중인 모든 장기 크루 불러오기
	public List<LongCrewRecruitDTO> selectLongCrewById(String memId) {
		List<LongCrewRecruitDTO> crewList = new ArrayList<LongCrewRecruitDTO>();
		String SELECT_CREW_BY_ID = "select longCrewRecruit.*, (select count(memId) from longCrewMember where crewName = longCrewRecruit.crewName) as totalCount from longCrewRecruit "
				+ "join longCrewMember on longCrewMember.crewName = longCrewRecruit.crewName where longCrewMember.memId = ?"; 
		
		try {
			psmt = con.prepareStatement(SELECT_CREW_BY_ID);
			psmt.setString(1, memId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				LongCrewRecruitDTO dto = new LongCrewRecruitDTO();
				dto.setCrewName(rs.getString(1));
				dto.setContent(rs.getString(2));
				dto.setMemberNum(rs.getInt(3));
				dto.setCreated(rs.getString(4));
				dto.setAdminId(rs.getString(5));
				dto.setTotalCount(rs.getInt("totalCount"));
				crewList.add(dto);
			}
			
			System.out.println("selectLongCrewById 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectLongCrewById 실패");
		}
		
		return crewList;
	}
	
	
	// 방금 단기크루에 등록된 크루 no 가져오기 & 방금 장기크루가 등록한 일정 no 가져오기
	public int selectNo() {
		int result = 0;
	
		String CHECK_CREWNAME_SQL = "select max(no) from crewRecruit";
		
		try {
			psmt = con.prepareStatement(CHECK_CREWNAME_SQL);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			System.out.println("selectNo 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectNo 실패");
		}
		
		return result;
	}
	
	
	// 장기크루 일정 모두 불러오기
	public List<CrewRecruitDTO> selectAllSchedule() {
		List<CrewRecruitDTO> scheduleList = new ArrayList<CrewRecruitDTO>();
		String SELECT_ALL_SCHEDULE = "select *, (select count(memId) from crewSchedule where no = crewRecruit.no)"
				+ " as totalCount from crewRecruit where isCrew = false order by created desc";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_SCHEDULE);
			
			while(rs.next()) {
				CrewRecruitDTO dto = new CrewRecruitDTO();
				dto.setNo(rs.getInt(1));
				dto.setCrewName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setLocation(rs.getString(5));
				dto.setMemberNum(rs.getInt(6));
				dto.setCreated(rs.getString(7));
				dto.setGatherDate(rs.getString(8));
				dto.setAdminId(rs.getString(9));
				dto.setCrew(rs.getBoolean(10));
				dto.setTotalCount(rs.getInt("totalCount"));
				scheduleList.add(dto);
			}
			
			
			System.out.println("selectAllSchedule 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectAllSchedule 실패");
		}
		
		
		return scheduleList;
	}
	
	// 방장 제외 0명이면서 모임일 지난 장기크루 일정 삭제
	public void deleteSchedule(int no, String gatherDate) {
		int result = 0;
		String DELETE_SCHEDULE = "delete from crewRecruit where no = ? and (select count(*) from crewSchedule where no = ?) <= 1 and isCrew = false";
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			// 크루 모집일 날짜
			Date gatherDate2 = formatter.parse(gatherDate);
			
			// 오늘 날짜
			Date now = new Date();
			String today = formatter.format(now);
			Date today2 = formatter.parse(today);
		
			// 모집일이 지났는지 확인
			int result2 = gatherDate2.compareTo(today2);
			
			// 오늘 날짜 지났으면 삭제
			if(result2 < 0) {
			
			psmt = con.prepareStatement(DELETE_SCHEDULE);
			psmt.setInt(1, no);
			psmt.setInt(2, no);
			result = psmt.executeUpdate();
			System.out.println("deleteSchedule 성공");
		}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("deleteSchedule 실패");
		}
	
	}
	
	
	// 특정 id가 개설한 단기 크루 모두 가져오기
	public List<CrewRecruitDTO> selectCrewsByAdmin(String memId) {
		List<CrewRecruitDTO> crewList = new ArrayList<CrewRecruitDTO>();
		String SELECT_CREWS_BY_ADMIN = "select crewRecruit.*, (select count(memId) from crewSchedule where crewName = crewRecruit.crewName) as totalCount from crewRecruit "
				+ "join crewSchedule on crewSchedule.crewName = crewRecruit.crewName where crewSchedule.memId = ? and memAdmin = true and isCrew = true";
		
		try {
			psmt = con.prepareStatement(SELECT_CREWS_BY_ADMIN);
			psmt.setString(1, memId);
			rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				CrewRecruitDTO dto = new CrewRecruitDTO();
				dto.setNo(rs.getInt(1));
				dto.setCrewName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setLocation(rs.getString(5));
				dto.setMemberNum(rs.getInt(6));
				dto.setCreated(rs.getString(7));
				dto.setGatherDate(rs.getString(8));
				dto.setAdminId(rs.getString(9));
				dto.setCrew(rs.getBoolean(10));
				dto.setTotalCount(rs.getInt("totalCount"));
				crewList.add(dto);
			}
			
			System.out.println("selectCrewsByAdmin 성공");

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectCrewsByAdmin 실패");
		}
		
		return crewList;
	}

	
	// 특정 id가 개설한 장기 크루 모두 가져오기
	public List<LongCrewRecruitDTO> selectLongCrewsByAdmin(String memId) {
		List<LongCrewRecruitDTO> crewList = new ArrayList<LongCrewRecruitDTO>();
		String SELECT_LONGCREWS_BY_ADMIN = "select longCrewRecruit.*, (select count(memId) from longCrewMember where crewName = longCrewRecruit.crewName) as totalCount from longCrewRecruit "
				+ "join longCrewMember on longCrewMember.crewName = longCrewRecruit.crewName where longCrewMember.memId = ? and isAdmin = true";
		
		try {
			psmt = con.prepareStatement(SELECT_LONGCREWS_BY_ADMIN);
			psmt.setString(1, memId);
			rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				LongCrewRecruitDTO dto = new LongCrewRecruitDTO();
				dto.setCrewName(rs.getString(1));
				dto.setContent(rs.getString(2));
				dto.setMemberNum(rs.getInt(3));
				dto.setCreated(rs.getString(4));
				dto.setAdminId(rs.getString(5));
				dto.setTotalCount(rs.getInt("totalCount"));
				crewList.add(dto);
			}
			
			System.out.println("selectLongCrewsByAdmin 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectLongCrewsByAdmin 실패");
		}
		
		return crewList;
	}
	
	//특정 크루의 방장 id 가져오기(단기 크루)
	public String selectAdminId(String crewName) {
		String adminId = "";
		String SELECT_ADMINID = "select adminId from crewRecruit where crewName = ?";
		
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
	
	
	// 특정 단기크루의 모든 memId 가져오기
	public List<CrewScheduleMemberDTO> selectMemid(String crewName) {
		List<CrewScheduleMemberDTO> memberList = new ArrayList<CrewScheduleMemberDTO>();
		String SELECT_MEMID = "select memId from crewSchedule where crewName = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_MEMID);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CrewScheduleMemberDTO dto = new CrewScheduleMemberDTO();
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

package model;

import common.DBConnector;

public class MemberDAO extends DBConnector {
	
	public MemberDAO() {
		super();
	}
	
	
	// 아이디, 비밀번호 일치여부 확인
	public int loginCheck(String id, String pw) {
		int result = 0;

		String LOGIN_CHECK_SQL = "select id, password from member where id=? and password=?";
		
		try {
			psmt = con.prepareStatement(LOGIN_CHECK_SQL);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
			
			System.out.println("LoginCheck 성공");
			
		} catch(Exception e) {
			System.out.println("LoginCheck 실패");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	// 일치하는 아이디 있는지 확인
	public int checkDuplicateId(String id) {
		int result = 0;
		
		String CHECK_ID_SQL = "select id from member where id=?";
		
		try {
			psmt = con.prepareStatement(CHECK_ID_SQL);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
			
			System.out.println("CheckDuplicateId 성공");

		} catch(Exception e) {
			System.out.println("CheckDuplicateId 실패");
			e.printStackTrace();
			
		}
		
		return result;
	}
	
	// 회원가입
	public int registerMember(MemberDTO dto) {
		int result = 0;
		String INSERT_MEMBER_SQL = "insert into member values (?, ? ,?, ?, ?, ?, ?, ?, ?, null, null, null, null, ?)";
		
		try {
			psmt = con.prepareStatement(INSERT_MEMBER_SQL);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPassword());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getBirthDate());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getNickname());
			psmt.setString(7, dto.getPhone());
			psmt.setString(8, dto.getRegisterDate());
			psmt.setString(9, dto.getLastVisitDate());
			psmt.setString(10, dto.getGender());
			result = psmt.executeUpdate();
			
			System.out.println("RegisterMember 성공");
			
		} catch (Exception e) {
			
			System.out.println("RegisterMember 실패");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	// ID로 멤버 모든 정보 가져오기
	public MemberDTO getMember(String id) {
		MemberDTO dto = new MemberDTO();
		String GET_MEMBER_SQL = "select * from member where id = ?";
		
		try {
			psmt = con.prepareStatement(GET_MEMBER_SQL);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {	
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setBirthDate(rs.getString("birthDate"));
				dto.setEmail(rs.getString("email"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPhone(rs.getString("phone"));
				dto.setRegisterDate(rs.getString("registerDate"));
				dto.setLastVisitDate(rs.getString("lastVisitDate"));
				dto.setScore(rs.getInt("score"));
				dto.setLocation(rs.getString("location"));
				dto.setTotal(rs.getString("total"));
			}
			
			System.out.println("GetMember 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("GetMember 실패");
			
		}
		
		return dto;
	}
	
	
	// 로그인시 마지막 접속일자 데이터에 저장
	public void update_LastVisitDate(String id, String lastVisitDate) {
		String UPDATE_LastVisitDate_SQL = "update member set lastVisitDate = ? where id = ?";
		int result = 0;
		
		try {
			psmt = con.prepareStatement(UPDATE_LastVisitDate_SQL);
			psmt.setString(1, lastVisitDate);
			psmt.setString(2, id);
			result = psmt.executeUpdate();

			if(result == 1) {
				System.out.println("Update_LastVisitDate 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update_LastVisitDate 실패");
		}
		
	}
	
	
	// 아이디 찾기
	public String findId(String name, String email, String birthDate) {
		String id = "";
		String FIND_ID_SQL = "select id from member where name=? and email=? and birthDate=?";
		
		try {
			psmt = con.prepareStatement(FIND_ID_SQL);
			psmt.setString(1, name);
			psmt.setString(2, email);
			psmt.setString(3, birthDate);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("id");
				System.out.println("FindId 성공");
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("FindId 실패");
		
		} finally {
			new MemberDAO().close();
		}
		
		return id;
	}
	
	
	// 비밀번호 찾기
	public String findPw(MemberDTO dto) {
		String pw = "";
		String FIND_PW_SQL = "select password from member where id=? and name=? and email=? and birthDate=?";
		
		try {
			psmt = con.prepareStatement(FIND_PW_SQL);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getBirthDate());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				pw = rs.getString("password");
				System.out.println("FindPw 성공");
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			System.out.println("FindPw 실패");
		
		} finally {
			new MemberDAO().close();
		}
		
		return pw;
	}
	
	
	// 비밀번호 재설정
	public int resetPw(String id, String pw) {
		int result = 0;
		String RESET_PW_SQL = "update member set password = ? where id = ?";
		
		try {
			psmt = con.prepareStatement(RESET_PW_SQL);
			psmt.setString(1, pw);
			psmt.setString(2, id);
			result = psmt.executeUpdate();
			
			System.out.println("ResetPw 성공");
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("ResetPw 실패");
		}
		
		return result;
	}

	
	// 회원탈퇴
	public int deleteMember(String id, String pw) {
		int result = 0;
		String DELETE_MEMBER_SQL = "delete from member where id = ? and password = ?";
		
		try {
			psmt = con.prepareStatement(DELETE_MEMBER_SQL);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			result = psmt.executeUpdate();
			System.out.println("deleteMember 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteMember 실패");
		}
		
		return result;
	}
	
	// 아이디로 멤버정보 가져오기
	public MemberDTO getMemberById(String id) {
		MemberDTO member = new MemberDTO();
		String GET_BYID_SQL = "select * from member where id = ?";
		
		try {
			 psmt = con.prepareStatement(GET_BYID_SQL);
			 psmt.setString(1, id);
			 rs = psmt.executeQuery();
					 
			if(rs.next()) {
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setBirthDate(rs.getString("birthDate"));
				member.setEmail(rs.getString("email"));
				member.setNickname(rs.getString("nickname"));
				member.setPhone(rs.getString("phone"));
				member.setLocation(rs.getString("location"));
				member.setProfileImg(rs.getString("profileImg"));
				member.setGender(rs.getString("gender"));
				System.out.println(rs.getString("birthDate"));
			}
			 
			 System.out.println("getMemberById 성공");
		 } catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("getMemberById 실패");
		 }
		 return member;
	}
	
	
	// 회원정보 수정
	public int updateMember(MemberDTO dto) {
		int result = 0;
		String UPDATE_MEMBER_SQL = "update member set password=?, birthDate=?, email=?,"
				+ " nickname=?, phone=?, location=?, profileImg=?, gender=?  where id=?";
		try {
			psmt = con.prepareStatement(UPDATE_MEMBER_SQL);
			psmt.setString(1, dto.getPassword());
			psmt.setString(2, dto.getBirthDate());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getNickname());
			psmt.setString(5, dto.getPhone());
			psmt.setString(6, dto.getLocation());
			psmt.setString(7, dto.getProfileImg());
			psmt.setString(8, dto.getGender());
			psmt.setString(9, dto.getId());
			result = psmt.executeUpdate();
			
			System.out.println("updateMember 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("updateMember 실패");
		}
		
		return result;
	}
	
	// 특정 id의 성별 가져오기
	public String selectGender(String memId) {
		String gender = "";
		String SELECT_GENDER = "select gender from member where id = ?";
		
		try {
			
			System.out.println("selectGender 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectGender 실패");
		}
		
		return gender;
	}
}
package model;

public class CrewScheduleMemberDTO {

	
	private int no, memberNum;
	private String crewName, memId;
	private boolean memAdmin;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getCrewName() {
		return crewName;
	}
	public void setCrewName(String crewName) {
		this.crewName = crewName;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public boolean isMemAdmin() {
		return memAdmin;
	}
	public void setMemAdmin(boolean memAdmin) {
		this.memAdmin = memAdmin;
	}
	
	
}

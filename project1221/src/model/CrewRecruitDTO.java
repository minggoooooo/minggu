package model;

public class CrewRecruitDTO {
	
	private String crewName, title, content, location, created, gatherDate, adminId, courseId;
	private int no, memberNum, totalCount;
	private boolean isCrew, complete, member;		// isMember : 일정에 유저가 참여되어있는지 확인하기 위해 만듦
													// crewPlannedSchedule에서 일정참여 버튼 출력 여부를 위해 사용
	
	
	public String getCrewName() {
		return crewName;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getLocation() {
		return location;
	}
	public String getCreated() {
		return created;
	}
	public String getGatherDate() {
		return gatherDate;
	}
	public String getAdminId() {
		return adminId;
	}
	public String getCourseId() {
		return courseId;
	}
	public int getNo() {
		return no;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public boolean isCrew() {
		return isCrew;
	}
	public void setCrewName(String crewName) {
		this.crewName = crewName;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public void setGatherDate(String gatherDate) {
		this.gatherDate = gatherDate;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public void setCrew(boolean isCrew) {
		this.isCrew = isCrew;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public boolean isMember() {
		return member;
	}
	public void setMember(boolean member) {
		this.member = member;
	}
	
	
	
}
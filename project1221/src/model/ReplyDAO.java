package model;

import java.util.ArrayList;
import java.util.List;

import common.DBConnector;

public class ReplyDAO extends DBConnector {

	// 게시판 댓글 등록
	public void registerReply(ReplyDTO dto) {
		int result = 0;
		String REGISTER_REPLY = "insert into reply values(null, ?, ?, ?, ?)";
		
		try {
			psmt = con.prepareStatement(REGISTER_REPLY);
			psmt.setInt(1, dto.getB_no());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getCreated());
			psmt.setString(4, dto.getId());
			result = psmt.executeUpdate();
			
			System.out.println("registerReply 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("registerReply 실패");
		}
	}
	
	
	// 특정 게시판 댓글 불러오기
	public List<ReplyDTO> selectReplyByNo(int b_no) {
		List<ReplyDTO> replyList = new ArrayList<ReplyDTO>();
		String SELECT_REPLY_BYNO = "select * from reply where b_no = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_REPLY_BYNO);
			psmt.setInt(1, b_no);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ReplyDTO dto = new ReplyDTO();
				dto.setR_no(rs.getInt(1));
				dto.setB_no(rs.getInt(2));
				dto.setContent(rs.getString(3));
				dto.setCreated(rs.getString(4));
				dto.setId(rs.getString(5));
				replyList.add(dto);
			}
			System.out.println("selectReplyByNo 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectReplyByNo 실패");
		}
		return replyList;
	}
	
	
}

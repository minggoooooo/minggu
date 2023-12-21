package model;

import java.util.ArrayList;
import java.util.List;

import common.DBConnector;

public class BoardDAO extends DBConnector {
	
	// 게시판 등록
	public int insertBoard(BoardDTO dto) {
		int result = 0;
		String INSERT_BOARD_SQL = "insert into crewboard values(0, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			psmt = con.prepareStatement(INSERT_BOARD_SQL);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getNickname());
			psmt.setString(3, dto.getCreated());
			psmt.setString(4, dto.getTitle());
			psmt.setString(5, dto.getContent());
			psmt.setString(6, dto.getCategory());
			psmt.setString(7, dto.getImgName());
			psmt.setString(8, dto.getCrewName());
			result = psmt.executeUpdate();
			
			System.out.println("InsertBoard 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("InsertBoard 실패");
		}
		
		return result;
	}
	
	
	// 게시판 전체 불러오기
	public List<BoardDTO> selectAllBoard() {
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		String SELECT_ALLBOARD_SQL = "select *, (select count(*) from recommend where no=crewboard.no) as count from crewboard";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_ALLBOARD_SQL);
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setCreated(rs.getString("created"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setCategory(rs.getString("category"));
				dto.setImgName(rs.getString("imgName"));
				dto.setCrewName(rs.getString("crewName"));
				dto.setCount(rs.getInt("count"));
				boardlist.add(dto);
				System.out.println("SelectAllBoard 성공");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("SelectAllBoard 실패");
		}
		
		
		return boardlist;
	}
	
	// 특정 크루의 모든 게시판 불러오기
	public List<BoardDTO> selectCrewBoardList(String crewName, int start) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		//int totalNum = selectCount(crewName) - start + 1;
		String SELECT_CREW_BOARD = "select *, (select count(*) from recommend where no=crewboard.no) as count from crewboard where crewName = ?"
				+ " order by created desc";
		
		int limit = 0;
		
		try {
			psmt = con.prepareStatement(SELECT_CREW_BOARD);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			rs.absolute(start - 1);
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setCreated(rs.getString("created"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setCategory(rs.getString("category"));
				dto.setImgName(rs.getString("imgName"));
				dto.setCrewName(rs.getString("crewName"));
				dto.setCount(rs.getInt("count"));
				list.add(dto);
				//totalNum--;
				limit++;
				if(limit == 7) {
					break;
				}
			}
			
			System.out.println("selectCrewBoardList 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectCrewBoardList 실패");
		}
		
		return list;
	}
	
	
	// 특정크루의 모든 게시물 개수 가져오기
	public int selectCount(String crewName) {
		int result = 0;
		String SELECT_COUNT = "select count(*) from crewboard where crewName = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_COUNT);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			System.out.println("selectCount 성공");

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectCount 실패");
		}
		
		
		return result;
	}

	
	// 카테고리별 게시판 불러오기
	public List<BoardDTO> selectCategoryBoard(String category) {
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		String SELECT_CATEBOARD_SQL = "select *, (select count(*) from recommend where no=crewboard.no) as count from crewboard where category = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_CATEBOARD_SQL);
			psmt.setString(1, category);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setCreated(rs.getString("created"));
				dto.setTitle(rs.getString("title"));
				dto.setCreated(rs.getString("content"));
				dto.setCreated(rs.getString("category"));
				dto.setCreated(rs.getString("imgName"));
				dto.setCount(rs.getInt("count"));
				boardlist.add(dto);
			}
			System.out.println("SelectAllBoard 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("SelectAllBoard 실패");
		}
		
		
		return boardlist;
	}
	
	// 한개의 게시판 상세보기 불러오기
	public BoardDTO selectBoard(int no) {
		BoardDTO dto = new BoardDTO();
		String SELECT_BOARD_SQL = "select *, (select count(*) from recommend where no=crewboard.no) as count from crewboard where no = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_BOARD_SQL);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setCreated(rs.getString("created"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setCategory(rs.getString("category"));
				dto.setImgName(rs.getString("imgName"));
				dto.setCount(rs.getInt("count"));
				dto.setCrewName(rs.getString("crewName"));
				
			}
			
			System.out.println("selectBoard 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectBoard 실패");
		}
	
		return dto;
	}
	
	
	public int deleteBoard(int no) {
		int result = 0;
		String DELETE_BOARD_SQL = "delete from crewboard where no = ?";
		
		try {
			psmt = con.prepareStatement(DELETE_BOARD_SQL);
			psmt.setInt(1, no);
			result = psmt.executeUpdate();
			System.out.println("deleteBoard 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("deleteBoard 실패");
		}
		return result;
		
	}
	
	
	public int editBoard(BoardDTO dto) {
		int result = 0;
		String EDIT_BOARD_SQL = "update crewboard set created = ?, title = ?, content = ?, category = ?, imgName = ? where no = ?";
	
		try {
			psmt = con.prepareStatement(EDIT_BOARD_SQL);
			psmt.setString(1, dto.getCreated());
			psmt.setString(2, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getCategory());
			psmt.setString(4, dto.getImgName());
			psmt.setInt(5, dto.getNo());
			result = psmt.executeUpdate();
			
			System.out.println("editBoard 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("editBoard 실패");
		}
		return result;
	}
	
	
	
}
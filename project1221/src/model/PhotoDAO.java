package model;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.PrintServiceAttribute;

import common.DBConnector;

public class PhotoDAO extends DBConnector {

	// 사진첩 업로드
	/*
	 * public int insertPhoto(photoDTO dto) { int result = 0; String
	 * INSERT_PHOTO_SQL = "insert into photobook values (0, ?, ?, ?, ?, ?)";
	 * 
	 * try { psmt = con.prepareStatement(INSERT_PHOTO_SQL); psmt.setString(1,
	 * dto.getId()); psmt.setString(2, dto.getTitle()); psmt.setString(3,
	 * dto.getContent()); psmt.setString(4, dto.getCreated()); psmt.setString(5,
	 * dto.getImgName()); result = psmt.executeUpdate();
	 * 
	 * System.out.println("insertPhoto 성공"); } catch(Exception e) {
	 * e.printStackTrace(); System.out.println("insertPhoto 실패"); }
	 * 
	 * return result; }
	 */

	// 사진첩 모두 불러오기
	/*
	 * public List<photoDTO> selectAllPhoto() { List<photoDTO> photoList = new
	 * ArrayList<photoDTO>(); String SELECT_ALLPHOTO_SQL =
	 * "select * from photobook";
	 * 
	 * try { psmt = con.prepareStatement(SELECT_ALLPHOTO_SQL); rs =
	 * psmt.executeQuery();
	 * 
	 * while(rs.next()) { photoDTO dto = new photoDTO();
	 * dto.setP_no(rs.getInt("p_no")); dto.setId(rs.getString("id"));
	 * dto.setTitle(rs.getString("title")); dto.setContent(rs.getString("content"));
	 * dto.setCreated(rs.getString("created"));
	 * dto.setImgName(rs.getString("imgName")); photoList.add(dto); }
	 * System.out.println("selectAllPhoto 성공"); } catch(Exception e) {
	 * e.printStackTrace(); System.out.println("selectAllPhoto 실패"); }
	 * 
	 * 
	 * return photoList; }
	 */

	// 사진 삭제
	public int deletePhoto(int no) {
		int result = 0;
		String DELETE_PHOTO_SQL = "update crewboard set imgName = null where no = ?";

		try {
			psmt = con.prepareStatement(DELETE_PHOTO_SQL);
			psmt.setInt(1, no);
			result = psmt.executeUpdate();

			System.out.println("deletePhoto 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deletePhoto 실패");
		}

		return result;
	}

	// 최신순으로 사진 가져오기
	public List<BoardDTO> selectPhotos() {
		List<BoardDTO> photoList = new ArrayList<BoardDTO>();
		String SELECT_PHOTOS_SQL = "SELECT imgName, no FROM crewboard ORDER BY created DESC";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_PHOTOS_SQL);

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setImgName(rs.getString("imgName"));
				dto.setNo(rs.getInt("no"));
				photoList.add(dto);
			}

			System.out.println("selectPhotos 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectPhotos 실패");
		}

		return photoList;
	}
	
	// 특정 크루의 모든 이미지(앨범) 가져오기
	public List<BoardDTO> selectPhotosByCrew(String crewName) {
		List<BoardDTO> photoList = new ArrayList<BoardDTO>();
		String SELECT_PHOTOS_BYCREW = "select imgName, crewName, no from crewboard where crewName = ?";
		
		try {
			psmt = con.prepareStatement(SELECT_PHOTOS_BYCREW);
			psmt.setString(1, crewName);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setImgName(rs.getString("imgName"));
				dto.setCrewName(rs.getString("crewName"));
				dto.setNo(rs.getInt("no"));
				photoList.add(dto);
			}
			
			System.out.println("selectPhotosByCrew 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("selectPhotosByCrew 실패");
		}
		return photoList;
	}
	
	
	
}
package model;

import common.DBConnector;

public class RecommendDAO extends DBConnector {

   // 추천버튼 횟수 업데이트
   public int updateRecommendCount(int no, String id) {
      int result = 0;
      String UPDATE_RECOMMEND_SQL = "insert into recommend values (?, ?)";

      try {
         psmt = con.prepareStatement(UPDATE_RECOMMEND_SQL);
         psmt.setInt(1, no);
         psmt.setString(2, id);
         result = psmt.executeUpdate();

         System.out.println("UpdateRecommendCount 성공");
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("UpdateRecommendCount 실패");
      }

      return result;
   }

   // 게시물 추천버튼 총횟수 확인

   public int SelectRecommendCount(int no) {
      int result = 0;
      String SELECT_RECOMMENDCOUNT_SQL = "select count(*) from recommend where no=?";

      try {
         psmt = con.prepareStatement(SELECT_RECOMMENDCOUNT_SQL);
         psmt.setInt(1, no);
         rs = psmt.executeQuery();

         if (rs.next()) {
            result = rs.getInt(1);
         }

         System.out.println("SelectRecommendCount 성공");
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("SelectRecommendCount 실패");
      }

      return result;
   }
   
   public int deleteRecommendCount(int no, String id) {
      int result = 0;
      String Delete_RECOMMEND_SQL = "delete from recommend where no = ? and id = ?";

      try {
         psmt = con.prepareStatement(Delete_RECOMMEND_SQL);
         psmt.setInt(1, no);
         psmt.setString(2, id);
         result = psmt.executeUpdate();

         System.out.println("UpdateRecommendCount 성공");
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("UpdateRecommendCount 실패");
      }

      return result;
   }
   
   public boolean checkRecommend(int no, String id) {
      boolean result = false;
      int result1 = 0;
      String Select_RECOMMEND_SQL = "select count(*) from recommend where no = ? and id = ?";

      try {
         psmt = con.prepareStatement(Select_RECOMMEND_SQL);
         psmt.setInt(1, no);
         psmt.setString(2, id);
         rs = psmt.executeQuery();
         if(rs.next()) {
            result1 = rs.getInt(1);
         }
         if(result1 == 1) {
            result = true;
         }

         System.out.println("UpdateRecommendCount 성공");
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("UpdateRecommendCount 실패");
      }
      
      return result;
   }

}
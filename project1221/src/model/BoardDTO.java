package model;

public class BoardDTO {

   private String id, nickname, created, title, content, category, imgName, crewName;
   private int no, count, recommend;
   
   
   public String getId() {
      return id;
   }
   public String getNickname() {
      return nickname;
   }
   public String getCreated() {
      return created;
   }
   public String getTitle() {
      return title;
   }
   public String getContent() {
      return content;
   }
   public String getCategory() {
      return category;
   }
   public String getImgName() {
      return imgName;
   }
   public String getCrewName() {
      return crewName;
   }
   public int getNo() {
      return no;
   }
   public int getCount() {
      return count;
   }
   public void setId(String id) {
      this.id = id;
   }
   public void setNickname(String nickname) {
      this.nickname = nickname;
   }
   public void setCreated(String created) {
      this.created = created;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public void setCategory(String category) {
      this.category = category;
   }
   public void setImgName(String imgName) {
      this.imgName = imgName;
   }
   public void setCrewName(String crewName) {
      this.crewName = crewName;
   }
   public void setNo(int no) {
      this.no = no;
   }
   public void setCount(int count) {
      this.count = count;
   }
   public int getRecommend() {
      return recommend;
   }
   public void setRecommend(int recommend) {
      this.recommend = recommend;
   }
   
   
   
   
}
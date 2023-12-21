package model;

import java.util.ArrayList;

public class Guestbook {
	private Integer no;
	private String id, owner_id, created, content , imgFile;
	private ArrayList<GuestbookReply> replyList;

	public Integer getNo() {
		return no;
	}

	public String getImgFile() {
		return imgFile;
	}

	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}

	public void setNo(Integer no) {
		this.no = no;	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<GuestbookReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(ArrayList<GuestbookReply> replyList) {
		this.replyList = replyList;
	}
	
}

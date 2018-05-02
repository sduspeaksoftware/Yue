package client;

import java.sql.Timestamp;

class DynamicReply{
	public int replyId;
	public int ownerDynamicId;
	public Timestamp replyTime;
	public String replyUsername;
	public String replyContent;
	
	/* 空构造，使用getServer()类似的方法获取回复 */
	public DynamicReply() {
		
	}
	public DynamicReply(int ownerDynamicId, String replyUsername, String replyContent) {
		this.replyTime = new Timestamp(System.currentTimeMillis()); 	//get time stamp
		this.ownerDynamicId = ownerDynamicId;
		this.replyUsername = replyUsername;
		this.replyContent = replyContent;
	}
	
}
public class Dynamic {
	public int dynamicId;
	public String publisherUsername;
	public Timestamp publishTime;
	public String dynamicContent;
	public DynamicReply[] dynamicReply;
}

package client;

import java.sql.Timestamp;

class DynamicReply{
	public int replyId;
	public String replyUsername;
	public String replyContent;
}
public class Dynamic {
	public int dynamicId;
	public String publisherUsername;
	public Timestamp publishTime;
	public String dynamicContent;
	public DynamicReply[] dynamicReply;
}

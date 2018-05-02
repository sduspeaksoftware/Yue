package client;

import java.sql.Timestamp;

class NoteReply{
	public int replyId;
	public int ownerNoteId;
	public Timestamp replyTime;
	public String replyUsername;
	public boolean acceptYue;
	public String replyContent;
	public NoteReply(int ownerNoteId, String replyUsername, boolean acceptYue, String replyContent) {
		this.replyTime = new Timestamp(System.currentTimeMillis()); 	//get time stamp
		this.ownerNoteId = ownerNoteId;
		this.replyUsername = replyUsername;
		this.acceptYue = acceptYue;
		this.replyContent = replyContent;
	}
}

public class Note {
	public int noteId;
	public boolean noteFinished;
	public String publisherUsername;
	public String noteTitle;
	public String noteDestination;
	public String partnerIntention;
	public String noteContent;
	public NoteReply[] noteReply;
}

package client;

class NoteReply{
	public int replyId;
	public String replyUsername;
	public boolean acceptYue;
	public String replyContent;
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

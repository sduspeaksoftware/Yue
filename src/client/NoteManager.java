package client;

public interface NoteManager {
	public boolean publishNote();
	public boolean editNote();
	public boolean replyNote();
	public boolean finishNote();
	public boolean deleteNote();
}

package client;

import java.sql.Date;

public class User implements BasicInformationManager, DynamicManager, NoteManager{
	public boolean loginstate = false;
	public String username;
	private String password;
	public String nickname;
	public String sex;
	public Date birthDate;
	@Override
	public boolean publishNote() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean editNote() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean replyNote() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean finishNote() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean deleteNote() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean publishDynamic() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean editDynamic() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean browseDynamic() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean replyDynamic() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean deleteDynamic() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean register(String username, String password, String nickname) {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean login(String username, String password) {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean changeBasicUserInformation(String nickname, String sex, Date birthDate) {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean changePassword(String oldPassword, String newPassword) {
		// TODO �Զ����ɵķ������
		return false;
	}
}

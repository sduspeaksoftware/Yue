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
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean editNote() {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean replyNote() {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean finishNote() {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean deleteNote() {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean publishDynamic() {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean editDynamic() {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean browseDynamic() {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean replyDynamic() {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean deleteDynamic() {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean register(String username, String password, String nickname) {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean login(String username, String password) {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean changeBasicUserInformation(String nickname, String sex, Date birthDate) {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public boolean changePassword(String oldPassword, String newPassword) {
		// TODO 自动生成的方法存根
		return false;
	}
}

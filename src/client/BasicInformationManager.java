package client;

import java.sql.Date;

public interface BasicInformationManager {
	public boolean register(String username, String password, String nickname);
	public boolean login(String username, String password);
	public boolean changeBasicUserInformation(String nickname, String sex, Date birthDate);
	public boolean changePassword(String oldPassword, String newPassword);
}

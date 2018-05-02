package client;

public interface BasicInformationManager {
	public boolean register(String username, String password, String nickname);
	public boolean login(String username, String password);
	public boolean changeBasicUserInformation();
	public boolean changePassword(String oldPassword, String newPassword);
}

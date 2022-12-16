package kr.or.ddit.commons.exception;

public class UserNotFoundException extends PKNotFoundException{

	private String memId;
	
	public UserNotFoundException(String memId) {
		this.memId = memId;
	}
	
	@Override
	public String getMessage() {
		return String.format("%s 아이디 회원이 존재하지 않음.", this.memId);
	}
}

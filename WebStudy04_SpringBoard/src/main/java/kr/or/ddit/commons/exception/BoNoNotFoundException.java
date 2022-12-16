package kr.or.ddit.commons.exception;

public class BoNoNotFoundException extends PKNotFoundException{

	private int boNo;
	
	public BoNoNotFoundException(int boNo) {
		this.boNo = boNo;
	}
	
	@Override
	public String getMessage() {
		return String.format("%s 게시판 번호가 존재하지 않음.", this.boNo);
	}
}

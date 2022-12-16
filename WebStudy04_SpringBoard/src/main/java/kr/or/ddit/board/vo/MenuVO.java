package kr.or.ddit.board.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuVO {
	
	public MenuVO(String menuURL, String menuText) {
		super();
		this.menuURL = menuURL;
		this.menuText = menuText;
	}
	private String menuURL;
	private String menuText;
	private String menuId;
	private String menuColor;

}

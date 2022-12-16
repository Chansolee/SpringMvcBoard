package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of ="boNo")
@ToString(exclude= {"boPass","boContent", "boFiles","attatchList"})
public class BoardVO implements Serializable{
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class}) //update
	private Integer boNo;
	private String boDate;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
	@NotBlank
	private String boTitle;
	@NotBlank(groups=InsertGroup.class)
	private String boWriter;
	@NotBlank(groups=InsertGroup.class)
	private String boIp;
	@Email
	private String boMail;
	@NotBlank(groups={Default.class, DeleteGroup.class})
	@JsonIgnore 
	private transient String boPass;
	private String boContent;
	
	@JsonIgnore
	private transient List<MultipartFile> boFiles;

	public void setBoFiles(List<MultipartFile> boFiles) {
		if(boFiles==null || boFiles.isEmpty()) return;
		this.boFiles = boFiles;
		this.attatchList = new ArrayList<>();
		for(MultipartFile file : boFiles) {
			if(file.isEmpty()) continue;
			attatchList.add(new AttatchVO(file));
		}
	}
	@JsonIgnore
	private transient int startNo;
	@JsonIgnore
	private transient List<AttatchVO> attatchList;
	@JsonIgnore
	private transient int[] delAttNos; //여러개의 파일이 있을수 있어서 배열로 잡음
}

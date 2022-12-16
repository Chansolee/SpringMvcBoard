package kr.or.ddit.board.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PagingVO<T> {
   
   public PagingVO(int screenSize, int blockSize) {
      super();
      this.screenSize = screenSize;
      this.blockSize = blockSize;
   } 
 
   private int totalRecord; //디비값
   private int currentPage; //클라이언트에서 세팅
   private int screenSize=10;
   private int blockSize=5;  
   private int totalPage;
   
   private int startRow;
   private int endRow;
   
   private int startPage;
   private int endPage;
   
   private SearchVO simpleCondition;
   private T detailCondition;
   
   private List<T> dataList;
   
   public void setDetailCondition(T detailCondition) {
	   this.detailCondition = detailCondition;
  }
   
   public void setTotalRecord(int totalRecord) {
      this.totalRecord = totalRecord;
      totalPage = (totalRecord+(screenSize-1)) / screenSize ;
   }
   
   public void setCurrentPage(int currentPage) {
      this.currentPage = currentPage;
      endRow = currentPage * screenSize;
      startRow = endRow - (screenSize - 1);
      endPage = blockSize * ((currentPage + (blockSize - 1)) / blockSize) ;
      startPage = endPage - (blockSize - 1);
   }
    
   public void setDataList(List<T> dataList) {
      this.dataList = dataList;
   }
   
   public void setSimpleContition(SearchVO simpleCondition) {
	   this.simpleCondition = simpleCondition;
   }
   
 //String pattern = "<a href='?page=%d'> %s </a>";
   String pattern = "<li class='%s'><a class='page-link' href='#' data-page='%d'> %s </a></li>";
   public String getPagingHTML() {
      StringBuffer html = new StringBuffer();
      
      endPage =  endPage > totalPage ? totalPage : endPage;
         
         html.append("<ul class='pagination'>");
      
      if(startPage > blockSize) { 
         html.append(String.format(pattern, "page-item" ,startPage-blockSize,"이전" )); //이전 버튼처리
      }else {
         html.append(String.format(pattern, "page-item disabled",startPage-blockSize,"이전" )); //버튼생성,disabled
      }
      
      for(int page = startPage ; page <= endPage ; page++) {
         
         if(currentPage == page) {
            html.append(String.format(pattern, "page-item active aria-current='page'", page,page)) ; //현재페이지
         }else {
            html.append(String.format(pattern, "page-item", page,page)) ; //현재페이지 아닌놈
         }
      }
      
      if(endPage < totalPage) {
         html.append(String.format(pattern, "page-item" ,endPage+1,"다음" )); //다음 버튼처리
      }else {
         html.append(String.format(pattern, "page-item disabled", endPage+1,"다음" )); //버튼생성,disabled
      }
      
      html.append("</ul>");
      
      //System.out.println(html.toString());
      
      return html.toString();
   }
}
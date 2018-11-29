package pojo;

public class Page {
	private int currenPageNo=3;
	private int pageSize=3;
	private int totalCount;
	private int totalPageCount;
	public int getCurrenPageNo() {
		return currenPageNo;
	}
	public void setCurrenPageNo(int currenPageNo) {
		this.currenPageNo = currenPageNo <= 0?1
				:currenPageNo >= this.totalPageCount ? this.totalPageCount : currenPageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalPageCount=this.totalCount==0?1
				:this.totalCount%this.pageSize==0 ?
						this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	

}

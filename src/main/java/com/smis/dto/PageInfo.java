package com.smis.dto;

public class PageInfo {

	private Integer pageSize; // 每页大小
	private Integer currRecordIndex; // 当前页第一条数据的索引

	/** web */
	private Integer totalCount; // 总记录条数
	private Integer currentPage; // 当前页号
	private Integer totalPage; // 总页数
	private Integer prevPage; // 上一页页号
	private Integer nextPage; // 下一页页号
	private Integer lastPage; // 最后一页页号
	private String orderColum; // 排序的字段名
	private String order; // 排序的方式

	public PageInfo() {
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
		if (pageSize != null) {
			setCurrRecordIndex((currentPage - 1) * pageSize);
		}
	}

	public Integer getCurrRecordIndex() {
		return currRecordIndex;
	}

	public void setCurrRecordIndex(Integer currRecordIndex) {
		this.currRecordIndex = currRecordIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if (currentPage != null) {
			setCurrRecordIndex((currentPage - 1) * pageSize);
		}
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(Integer prevPage) {
		this.prevPage = prevPage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public String getOrderColum() {
		return orderColum;
	}

	public void setOrderColum(String orderColum) {
		this.orderColum = orderColum;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setPageInfo() {
		totalPage = (totalCount + pageSize - 1) / pageSize;
		prevPage = currentPage > 1 ? currentPage - 1 : currentPage;
		nextPage = currentPage < totalPage ? currentPage + 1 : currentPage;
	}

}
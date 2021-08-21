package com.apising.common.lang.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {

	private static final long serialVersionUID = -4371002973420231509L;

	private static final int DefaultCurPage = 1;
	private static final int DefaultPageSize = 20;

	private Integer curPage;

	private Integer pageSize;

	private List<T> list;

	private Integer totalNum;

	private List<Integer> pageIndexs;

	private int prePage; // 前一页
	private int nextPage; // 后一页

	private int firstPage; // 第一页
	private int lastPage; // 最后一页

	private Long startIdForPage; // 当前查询分页的起始ID，为提高查询效率用，适合于后台任务的单线程和多线程用
	private Long endIdForPage; // 当前查询分页的结束ID，为提高查询效率用，适合于后台任务的多线程用

	private Boolean needPage; // 是否需要分页 1:是， 0:否

	public Boolean isNeedPage() {
		if (null == needPage) {
			needPage = true;
		}
		return needPage;
	}

	public void setNeedPage(boolean needPage) {
		this.needPage = needPage;
	}

	public Long getStartIdForPage() {
		return startIdForPage;
	}

	public void setStartIdForPage(Long startIdForPage) {
		this.startIdForPage = startIdForPage;
	}

	public Long getEndIdForPage() {
		return endIdForPage;
	}

	public void setEndIdForPage(Long endIdForPage) {
		this.endIdForPage = endIdForPage;
	}

	private void resetPage() {
		if (0 > totalNum) {
			totalNum = 0;
		}
		if (0 > pageSize) {
			pageSize = DefaultPageSize;
		}
		if (0 > curPage) {
			curPage = DefaultCurPage;
		}
		if (1 > prePage || 1 >= curPage) {
			curPage = 1;
		}
		if (1 > nextPage) {
			nextPage = 1;
		}
		firstPage = 1;
		lastPage = 1;
	}

	protected void parsePage() {
		firstPage = 1;

		pageIndexs = new ArrayList<Integer>();
		if (0 >= totalNum) {
			resetPage();
			return;
		}
		int curPage = getCurPage();
		int pageSize = getPageSize();

		lastPage = (0 == totalNum % pageSize) ? totalNum / pageSize : totalNum / pageSize + 1;
		if (curPage >= lastPage) {
			curPage = lastPage;
		}
		int pageRange = (0 == curPage % 5) ? curPage / 5 : curPage / 5 + 1;
		int endPage = 0 >= pageRange ? 1 : Math.min(lastPage, pageRange * 5);
		int startPage = 0 >= pageRange ? 1 : Math.max(firstPage, (pageRange - 1) * 5 + 1);
		for (int i = startPage; i <= endPage; i++) {
			pageIndexs.add(i);
		}

		prePage = 1 >= curPage ? 1 : curPage - 1;
		nextPage = lastPage <= curPage ? 1 : curPage + 1;

	}

	public Page() {
		this.curPage = DefaultCurPage;
		this.pageSize = DefaultPageSize;
	}

	public Page(Page<T> page) {
		if (null == page) {
			return;
		}
		this.curPage = page.curPage;
		this.pageSize = page.pageSize;
		this.totalNum = page.totalNum;
		this.firstPage = page.firstPage;
		this.lastPage = page.lastPage;
		this.nextPage = page.nextPage;
		this.pageIndexs = page.pageIndexs;
		this.prePage = page.prePage;
		this.list = page.list;
	}

	public void setPage(Page<T> page) {
		if (null == page) {
			return;
		}
		this.curPage = page.curPage;
		this.pageSize = page.pageSize;
		this.totalNum = page.totalNum;
		this.firstPage = page.firstPage;
		this.lastPage = page.lastPage;
		this.nextPage = page.nextPage;
		this.pageIndexs = page.pageIndexs;
		this.prePage = page.prePage;
	}

	/**
	 * 得到总页数
	 * 
	 * @return
	 */
	public Integer getPageNum() {
		Integer pageNum = getTotalNum() / getPageSize();
		return (0 == getTotalNum() % getPageSize()) ? pageNum : pageNum + 1;
	}

	/**
	 * 返回 当前页
	 * 
	 * @return
	 */
	public Integer getCurPage() {
		return (0 >= curPage) ? DefaultCurPage : curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = (0 > curPage) ? DefaultCurPage : curPage;
	}

	/**
	 * 返回 每页的记录个数
	 * 
	 * @return
	 */
	public Integer getPageSize() {
		return (0 >= pageSize) ? DefaultPageSize : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = (0 > pageSize) ? 0 : pageSize;
	}

	/**
	 * 返回 记录总数
	 * 
	 * @return
	 */
	public Integer getTotalNum() {
		return totalNum != null ? totalNum : 0;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = (0 > totalNum) ? 0 : totalNum;
	}

	/**
	 *
	 * @return
	 */
	public Page<T> toPage() {
		parsePage();
		Page<T> page = new Page<>();
		page.setCurPage(curPage);
		page.setPageSize(pageSize);
		page.setTotalNum(totalNum);
		page.setFirstPage(firstPage);
		page.setLastPage(lastPage);
		page.setNextPage(nextPage);
		page.setPageIndexs(pageIndexs);
		page.setPrePage(prePage);
		page.setList(list);
		return page;
	}

	public List<Integer> getPageIndexs() {
		return pageIndexs;
	}

	public void setPageIndexs(List<Integer> pageIndexs) {
		this.pageIndexs = pageIndexs;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	@Override
	public String toString() {
		return "Page [curPage=" + curPage + ", pageSize=" + pageSize + ", totalNum=" + totalNum + ", pageIndexs="
				+ pageIndexs + ", prePage=" + prePage + ", nextPage=" + nextPage + ", firstPage=" + firstPage
				+ ", lastPage=" + lastPage + "]";
	}

	public void setPageInfo(Page<T> pager) {
		if (pager == null)
			return;
		this.curPage = pager.getCurPage();
		this.pageSize = pager.getPageSize();
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}
}

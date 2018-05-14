package com.whirly.form;

public class BaseSearchForm {
	private Integer page = 1;

	private Integer limit = 10;

	private String q;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		q = q.trim();
		if (q == null || q == "") {
			this.q = null;

		} else {
			this.q = "%" + q + "%";
		}

	}

	@Override
	public String toString() {
		return "BaseSearchForm [page=" + page + ", limit=" + limit + ", q=" + q + "]";
	}

}

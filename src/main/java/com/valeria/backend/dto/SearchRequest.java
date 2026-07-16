package com.valeria.backend.dto;
import com.valeria.backend.model.*;
import java.util.List;
public class SearchRequest {

    private int page;
    private int limit;
    private Search search;
    private List<SortRequest> sort;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public List<SortRequest> getSort() {
		return sort;
	}
	public void setSort(List<SortRequest> sort) {
		this.sort = sort;
	}

    
}
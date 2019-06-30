package com.pugerp.androidquiz.model;

import java.util.List;
import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@SerializedName("Response")
	@Expose
	private String response;

	@SerializedName("totalResults")
	@Expose
	private String totalResults;

	@SerializedName("Search")
	@Expose
	private List<SearchItem> search;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	public void setTotalResults(String totalResults){
		this.totalResults = totalResults;
	}

	public String getTotalResults(){
		return totalResults;
	}

	public void setSearch(List<SearchItem> search){
		this.search = search;
	}

	public List<SearchItem> getSearch(){
		return search;
	}

	@Override
 	public String toString(){
		return 
			"Response{" +
			"response = '" + response + '\'' + 
			",totalResults = '" + totalResults + '\'' + 
			",search = '" + search + '\'' + 
			"}";
		}
}
package com.pugerp.androidquiz.model;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class SearchItem{

	@SerializedName("Type")
	@Expose
	private String type;

	@SerializedName("Year")
	@Expose
	private String year;

	@SerializedName("imdbID")
	@Expose
	private String imdbID;

	@SerializedName("Poster")
	@Expose
	private String poster;

	@SerializedName("Title")
	@Expose
	private String title;

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
	}

	public void setImdbID(String imdbID){
		this.imdbID = imdbID;
	}

	public String getImdbID(){
		return imdbID;
	}

	public void setPoster(String poster){
		this.poster = poster;
	}

	public String getPoster(){
		return poster;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"SearchItem{" + 
			"type = '" + type + '\'' + 
			",year = '" + year + '\'' + 
			",imdbID = '" + imdbID + '\'' + 
			",poster = '" + poster + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}
package com.adisalagic.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackPost {


	@SerializedName("text")
	@Expose
	private String  text;
	@SerializedName("rate")
	@Expose
	private Integer rate;
	@SerializedName("status")
	@Expose
	private Integer status;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}

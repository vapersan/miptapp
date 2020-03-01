package com.adisalagic.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainData {
	@SerializedName("Средний балл")
	@Expose
	private double avarageScore;


	@SerializedName("Рейтинг по курсу")
	@Expose
	private int ratingByCourse;

	@SerializedName("Рейтинг по ФШ")
	@Expose
	private int ratingByFsh;

	@SerializedName("Статус")
	@Expose
	private String status;

	@SerializedName("Уровень образования")
	@Expose
	private String eduLevel;

	@SerializedName("Курс")
	@Expose
	private String course;

	@SerializedName("Подразделение")
	@Expose
	private String podr;

	@SerializedName("Группа")
	@Expose
	private String group;

	@SerializedName("Направление")
	@Expose
	private String diraction;

	@SerializedName("Программа")
	@Expose
	private String programme;

	@SerializedName("Форма оплаты обучения")
	@Expose
	private String payForm;

	@SerializedName("Базовая кафедра")
	@Expose
	private String baseCaf;

	@SerializedName("Научный руководтель")
	@Expose
	private String scienceHelper;

	@SerializedName("Тема дипломной работы")
	@Expose
	private String themeOfDiplome;

	public String getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getPodr() {
		return podr;
	}

	public void setPodr(String podr) {
		this.podr = podr;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDiraction() {
		return diraction;
	}

	public void setDiraction(String diraction) {
		this.diraction = diraction;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getPayForm() {
		return payForm;
	}

	public void setPayForm(String payForm) {
		this.payForm = payForm;
	}

	public String getBaseCaf() {
		return baseCaf;
	}

	public void setBaseCaf(String baseCaf) {
		this.baseCaf = baseCaf;
	}

	public String getScienceHelper() {
		return scienceHelper;
	}

	public void setScienceHelper(String scienceHelper) {
		this.scienceHelper = scienceHelper;
	}

	public String getThemeOfDiplome() {
		return themeOfDiplome;
	}

	public void setThemeOfDiplome(String themeOfDiplome) {
		this.themeOfDiplome = themeOfDiplome;
	}

	public double getAvarageScore() {
		return avarageScore;
	}

	public void setAvarageScore(double avarageScore) {
		this.avarageScore = avarageScore;
	}

	public int getRatingByCourse() {
		return ratingByCourse;
	}

	public void setRatingByCourse(int ratingByCourse) {
		this.ratingByCourse = ratingByCourse;
	}

	public int getRatingByFsh() {
		return ratingByFsh;
	}

	public void setRatingByFsh(int ratingByFsh) {
		this.ratingByFsh = ratingByFsh;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

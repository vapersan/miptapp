package com.adisalagic.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalData {

	@SerializedName("Фото")
	@Expose
	private
	String imageUrl;


	@SerializedName("ФИО")
	@Expose
	private
	String name;


	@SerializedName("Дата рождения")
	@Expose
	private
	String birthday;


	@SerializedName("Паспорт")
	@Expose
	private
	String passport;


	@SerializedName("СНИЛС")
	@Expose
	private
	String snils;


	@SerializedName("Email")
	@Expose
	private
	String email;


	@SerializedName("Адрес по прописке физ. лица")
	@Expose
	private
	String addressDoc;

	@SerializedName("Адрес проживания физ. лица")
	@Expose
	private
	String addressReal;

	@SerializedName("Уровень обучения")
	@Expose
	private
	String eduLevel;

	@SerializedName("Статус")
	@Expose
	private
	String status;

	@SerializedName("Период обучения")
	@Expose
	private
	String datePeriod;

	@SerializedName("Факультет")
	@Expose
	private
	String facultet;

	@SerializedName("Курс")
	@Expose
	private
	String course;

	@SerializedName("Группа")
	@Expose
	private
	String group;

	@SerializedName("Базовая кафедра")
	@Expose
	private
	String baseCaf;

	@SerializedName("Форма обучения")
	@Expose
	private
	String formOfStudy;

	@SerializedName("Источник финансирования")
	@Expose
	private
	String sourceOfFinance;

	@SerializedName("Должность")
	@Expose
	private
	String workPosition;

	@SerializedName("Ставка")
	@Expose
	private
	String workDevidents;

	@SerializedName("Отец")
	@Expose
	private
	String familyFather;

	@SerializedName("Сестра")
	@Expose
	private
	String familySister;

	@SerializedName("Мать")
	@Expose
	private
	String familyMother;


	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getSnils() {
		return snils;
	}

	public void setSnils(String snils) {
		this.snils = snils;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressDoc() {
		return addressDoc;
	}

	public void setAddressDoc(String addressDoc) {
		this.addressDoc = addressDoc;
	}

	public String getAddressReal() {
		return addressReal;
	}

	public void setAddressReal(String addressReal) {
		this.addressReal = addressReal;
	}

	public String getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDatePeriod() {
		return datePeriod;
	}

	public void setDatePeriod(String datePeriod) {
		this.datePeriod = datePeriod;
	}

	public String getFacultet() {
		return facultet;
	}

	public void setFacultet(String facultet) {
		this.facultet = facultet;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getBaseCaf() {
		return baseCaf;
	}

	public void setBaseCaf(String baseCaf) {
		this.baseCaf = baseCaf;
	}

	public String getFormOfStudy() {
		return formOfStudy;
	}

	public void setFormOfStudy(String formOfStudy) {
		this.formOfStudy = formOfStudy;
	}

	public String getSourceOfFinance() {
		return sourceOfFinance;
	}

	public void setSourceOfFinance(String sourceOfFinance) {
		this.sourceOfFinance = sourceOfFinance;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

	public String getWorkDevidents() {
		return workDevidents;
	}

	public void setWorkDevidents(String workDevidents) {
		this.workDevidents = workDevidents;
	}

	public String getFamilyFather() {
		return familyFather;
	}

	public void setFamilyFather(String familyFather) {
		this.familyFather = familyFather;
	}

	public String getFamilySister() {
		return familySister;
	}

	public void setFamilySister(String familySister) {
		this.familySister = familySister;
	}

	public String getFamilyMother() {
		return familyMother;
	}

	public void setFamilyMother(String familyMother) {
		this.familyMother = familyMother;
	}
}

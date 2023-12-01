package fr.diginamic.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "FILM")
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private float rating;

	private String url;

	private String summary;

	private int year_start;

	private int year_end;

	public Film() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getYear_start() {
		return year_start;
	}

	public void setYear_start(int year_start) {
		this.year_start = year_start;
	}

	public int getYear_end() {
		return year_end;
	}

	public void setYear_end(int year_end) {
		this.year_end = year_end;
	}

}

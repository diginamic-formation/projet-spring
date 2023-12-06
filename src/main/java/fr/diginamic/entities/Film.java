package fr.diginamic.entities;

import java.util.Objects;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "FILM")
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private Float rating;

	private String url;

	private String summary;
	@Column(name = "year_start")
	private int yearStart;
	@Column(name = "year_end")
	private int yearEnd;
	@Column(name = "reference_number")
	private String referenceNumber;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	@OneToMany(mappedBy = "film")
	private Set<Role> roleSet;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToOne
	@JoinColumn(name = "place_id")
	private Place place;

	// @JsonIgnore
	@ManyToMany
	@JoinTable(name = "FILM_GENRE", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
	private Set<Genre> genreSet;

	@ManyToMany
	@JoinTable(name = "REALISATORS_FILMS", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
	private Set<Person> personSet;

	public Film() {
	}

	public Film(int id) {
		this.id = id;
	}

	public Film(String title, Float rating, String url, String summary, int yearStart, int yearEnd,
			String referenceNumber, Language language, Set<Role> roleSet, Country country, Place place,
			Set<Genre> genreSet, Set<Person> personSet) {
		this.title = title;
		this.rating = rating;
		this.url = url;
		this.summary = summary;
		this.yearStart = yearStart;
		this.yearEnd = yearEnd;
		this.referenceNumber = referenceNumber;
		this.language = language;
		this.roleSet = roleSet;
		this.country = country;
		this.place = place;
		this.genreSet = genreSet;
		this.personSet = personSet;
	}

	public Film(String title, Float rating, String url, String summary, int yearStart, int yearEnd,
			String referenceNumber) {
		this.title = title;
		this.rating = rating;
		this.url = url;
		this.summary = summary;
		this.yearStart = yearStart;
		this.yearEnd = yearEnd;
		this.referenceNumber = referenceNumber;
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

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
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

	public int getYearStart() {
		return yearStart;
	}

	public void setYearStart(int yearStart) {
		this.yearStart = yearStart;
	}

	public int getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(int yearEnd) {
		this.yearEnd = yearEnd;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Set<Genre> getGenreSet() {
		return genreSet;
	}

	public void setGenreSet(Set<Genre> genreSet) {
		this.genreSet = genreSet;
	}

	public Set<Person> getPersonSet() {
		return personSet;
	}

	public void setPersonSet(Set<Person> personSet) {
		this.personSet = personSet;
	}

	@Override
	public String toString() {
		return "Film{" + "id=" + id + ", title='" + title + '\'' + ", rating=" + rating + ", url='" + url + '\''
				+ ", summary='" + summary + '\'' + ", yearStart=" + yearStart + ", yearEnd=" + yearEnd
				+ ", referenceNumber='" + referenceNumber + '\'' + ", language=" + language + ", country=" + country
				+ ", place=" + place + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Film film = (Film) o;
		return yearStart == film.yearStart && yearEnd == film.yearEnd && Objects.equals(title, film.title)
				&& Objects.equals(referenceNumber, film.referenceNumber) && Objects.equals(country, film.country);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, yearStart, yearEnd, referenceNumber, country);
	}
}

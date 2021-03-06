package daw.videoclub.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String url;
	private String description;
	private String year;
	private String director;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> cast;
	private String front;
	private String rating;

	public Movie() {
	}
	
	public Movie(String name, String url) {
		super();
		this.name = name;
		this.url = "<iframe width=\"560\" height=\"315\" src=\"" + url + "\" frameborder=\"0\" allowfullscreen></iframe>";
	}

	public Movie(String name, String url, String description,
			String year, String director, List<String> cast, String front,
			String rating) {
		super();
		this.name = name;
		this.url = url;
		this.description = description;
		this.year = year;
		this.director = director;
		this.cast = cast;
		this.front = front;
		this.rating = rating;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = "<iframe width=\"560\" height=\"315\" src=\"" + url + "\" frameborder=\"0\" allowfullscreen></iframe>";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<String> getCast() {
		return cast;
	}

	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}

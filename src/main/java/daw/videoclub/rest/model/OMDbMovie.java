package daw.videoclub.rest.model;


public class OMDbMovie {
	private String Title;
	private String Plot;
	private String Year;
	private String Director;
	private String Released;
	private String Runtime;
	private String Genre;
	private String Actors;
	private String imdbRating;
	private String Poster;
	
	public OMDbMovie(){
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String plot) {
		this.Plot = plot;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		this.Year = year;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		this.Director = director;
	}

	public String getReleased() {
		return Released;
	}

	public void setReleased(String released) {
		this.Released = released;
	}

	public String getRuntime() {
		return Runtime;
	}

	public void setRuntime(String runtime) {
		this.Runtime = runtime;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		this.Genre = genre;
	}

	public String getActors() {
		return Actors;
	}

	public void setActors(String actors) {
		this.Actors = actors;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String poster) {
		this.Poster = poster;
	}
	
	
	
}

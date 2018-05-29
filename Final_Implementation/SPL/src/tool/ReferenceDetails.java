package tool;

public class ReferenceDetails {
	private String authors;
	private String article;
	
	public ReferenceDetails( String authors, String article) {
		//this.refNum = refNum;
		this.authors = authors;
		this.article = article;
	}
	
	public String getAuthors() {
		return authors;
	}
	public String getArticle(){
		return article;
	}

}

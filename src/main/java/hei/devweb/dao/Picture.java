package hei.devweb.dao;

public class Picture {
	
	public Picture(Integer id, String name, String summary) {
		super();
		this.id = id;
		this.name = name;
		this.summary = summary;
	}

	private Integer id;
	private String name;
	private String summary;
	
	public Picture(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}


}

package com.kp.arteon.models;

public class ArtworkModel 
{
	
	
	public ArtworkModel(Long userId, String details, String url )
	{
		this.artDetails=details;
		this.userId=userId;
		this.imageUrl=url;
	}
	
	private Long userId;
	private String artDetails;
	private String imageUrl;
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getArtDetails() {
		return artDetails;
	}
	public void setArtDetails(String artDetails) {
		this.artDetails = artDetails;
	}

	

}

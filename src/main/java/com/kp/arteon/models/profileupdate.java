package com.kp.arteon.models;

public class profileupdate 
{
	private Long userId;
    private String username;
    private String email;
    private String password;
    private String photourl;
	
	
	    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

		

	    // Getters and setters

	    public profileupdate(Long userId, String username, String email, String password, String photourl)
	    {
	        this.userId = userId;
	        this.username = username;
	        this.email = email;
	        this.password = password;
	        this.photourl = photourl;
	    }
	

}

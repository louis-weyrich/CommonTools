package com.sos.tools.test.configuration;

public class JdbcConnection {
	
	private String driver;
	private String url;
	private String userName;
	private String password;
	private Integer port;
	private Boolean activate;
	private JdbcConnection secureConnection;

	public JdbcConnection() {
		// TODO Auto-generated constructor stub
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Boolean getActivate() {
		return activate;
	}

	public void setActivate(Boolean activate) {
		this.activate = activate;
	}
	
	public JdbcConnection getSecureConnection() {
		return secureConnection;
	}

	public void setSecureConnection(JdbcConnection secureConnection) {
		this.secureConnection = secureConnection;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("driver = ").append(driver).append("\n");
		buffer.append("url = ").append(url).append("\n");
		buffer.append("userName = ").append(userName).append("\n");
		buffer.append("password = ").append(password).append("\n");
		buffer.append("port = ").append(port).append("\n");
		buffer.append("activate = ").append(activate).append("\n\n");
		
		if(secureConnection != null)
		{
			buffer.append("secureConnection = ").append(secureConnection).append("\n");
		}
		
		return buffer.toString();
	}

}

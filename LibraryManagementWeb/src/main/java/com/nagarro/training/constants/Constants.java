package com.nagarro.training.constants;

public class Constants {
	// HQL Query for User
	public static final String GET_USER_QUERY = "FROM User WHERE username=:u";
	
	// Session Data Attributes
	public static final String USER_SESSION_ATTRIBUTE = "user";

	// Login Form Attribute
	public static final String USERNAME_FIELD = "username";
	public static final String PASSWORD_FIELD = "password";
	public static final String USERNAME_ERROR_FIELD = "username_error";
	public static final String PASSWORD_ERROR_FIELD = "password_error";

	// Exceptions
	public static final String UNKNOWN_ERROR = "Some Error Occurred.";
	public static final String USERNAME_DOES_NOT_EXISTS = "Username does not exists.";
	public static final String INCORRECT_PASSWORD = "Incorrect Password.";
	public static final String RESPONSE_BODY_PARSING_ERROR = "Error Parsing Response Body!";
	public static final String RETRIEVING_RESPONSE_ERROR = "Error Retrieving Books From REST Server!";
}

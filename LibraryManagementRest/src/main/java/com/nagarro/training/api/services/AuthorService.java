package com.nagarro.training.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.training.api.dao.AuthorDao;
import com.nagarro.training.api.models.Author;
import com.nagarro.training.constants.Constants;

@Component
public class AuthorService {
	ObjectMapper objMapper = new ObjectMapper();

	@Autowired
	AuthorDao authorDao;
	@Autowired
	Author author;

	// Method to list all Authors from the database
	public String listAllAuthor() {
		String resp = null;

		try {
			resp = objMapper.writeValueAsString(authorDao.findAll());
		} catch (JsonProcessingException | NumberFormatException e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.JSON_PROCESSING_ERROR + "\"}";
			e.printStackTrace();
		} catch (Exception e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.UNKNOWN_ERROR + "\"}";
		}
		return resp;
	}

	public String getAuthorById(int aid) {
		String resp = null;

		try {
			resp = objMapper.writeValueAsString(authorDao.findById(aid).orElse(null));
		} catch (JsonProcessingException | NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.UNKNOWN_ERROR + "\"}";
		}

		return resp;
	}

	public String addAuthor(Author newAuthor) {
		String resp = null;
		try {
			Author checkAuthor = authorDao.findById(newAuthor.getAid()).orElse(null);
			if (checkAuthor == null) {
				author.setAid(newAuthor.getAid());
				author.setAname(newAuthor.getAname());

				resp = objMapper.writeValueAsString(authorDao.save(author));
			} else {
				resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.AUTHOR_ALREADY_EXISTS + "\"}";
			}

		} catch (NumberFormatException | ClassCastException | NullPointerException | JsonProcessingException e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.JSON_PROCESSING_ERROR + "\"}";
		} catch (IllegalArgumentException iae) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.AUTHOR_NOT_AVAILABLE + "\"}";
		} catch (Exception e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.UNKNOWN_ERROR + " \"}";
		}

		return resp;

	}

	public String updateAuthor(Author newAuthor) {
		String resp = null;
		try {
			Author author = authorDao.findById(newAuthor.getAid()).orElse(null);
			if (author != null) {
				author.setAname(newAuthor.getAname());
				resp = objMapper.writeValueAsString(authorDao.save(author));
			} else {
				resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.AUTHOR_NOT_AVAILABLE + "\"}";
			}

		} catch (NumberFormatException | ClassCastException | NullPointerException | JsonProcessingException e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.JSON_PROCESSING_ERROR + "\"}";
		} catch (IllegalArgumentException iae) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.AUTHOR_NOT_AVAILABLE + "\"}";
		} catch (Exception e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.UNKNOWN_ERROR + "\"}";
		}

		return resp;
	}
}

package com.nagarro.training.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.api.models.Author;
import com.nagarro.training.api.services.AuthorService;
import com.nagarro.training.constants.Constants;

@RestController
@RequestMapping(Constants.AUTHOR_API_ROOT)
public class AuthorAPIController {
	@Autowired
	AuthorService authorService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public String fetchAuthors(@RequestParam(required = false) String aid) {
		if (aid == null) {
			// if bid is not present return all books
			return authorService.listAllAuthor();
		} else {
			// if bid is present return only one book
			return authorService.getAuthorById(Integer.parseInt(aid));
		}
	}

	@PostMapping(value = Constants.AUTHOR_API_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addAuthor(@RequestBody Author author) {
		return authorService.addAuthor(author);
	}

	@PutMapping(value = Constants.AUTHOR_API_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateAuthor(@RequestBody Author author) {
		return authorService.updateAuthor(author);
	}
}

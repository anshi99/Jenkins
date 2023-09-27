package com.nagarro.training.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.training.api.models.Author;

public interface AuthorDao extends JpaRepository<Author, Integer> {

}

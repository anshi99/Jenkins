package com.nagarro.training.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.training.api.models.Book;

public interface BookDao extends JpaRepository<Book, Integer> {

}

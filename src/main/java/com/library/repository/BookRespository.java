package com.library.repository;

import com.library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRespository extends JpaRepository<Book, Long> {
    List<Book> findAllByTitle(String title);

    Page<Book> findByTitleContainingOrCategoryContaining(String title, String category, Pageable pageable);
}

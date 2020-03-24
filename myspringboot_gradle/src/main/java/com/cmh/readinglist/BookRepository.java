package com.cmh.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: meice Huang
 * @date: 2020/3/22 下午5:55
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);
}

package com.example.library.borrowing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    @EntityGraph(attributePaths = {"client", "book"})
    Page<Borrowing> findAllByOrderByBorrowedAtDescIdDesc(Pageable pageable);

    @Query("select new com.example.library.borrowing.BorrowingReportItem(" +
            "c.fullName, c.birthDate, b.title, b.author, b.isbn, br.borrowedAt) " +
            "from Borrowing br " +
            "join br.client c " +
            "join br.book b " +
            "order by br.borrowedAt desc, br.id desc")
    List<BorrowingReportItem> findAllForReport();
}

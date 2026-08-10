package com.example.library.borrowing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
public class BorrowingRestController {

    private final BorrowingService borrowingService;

    public BorrowingRestController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping
    public List<BorrowingReportItem> getReaders() {
        return borrowingService.getReport();
    }
}

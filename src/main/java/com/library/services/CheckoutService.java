package com.library.services;

import com.library.models.Book;
import com.library.models.Checkout;
import com.library.repository.BookRespository;
import com.library.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {
    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private BookRespository bookRespository;

    public Book checkoutBook(String userEmail, Long bookId) throws Exception {
        Optional<Book> book = bookRespository.findById(bookId);
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if (!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0) {
            throw new Exception("Book doesn't exist or already checked out.");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRespository.save(book.get());

        Checkout checkout = new Checkout();
        checkout.setUserEmail(userEmail);
        checkout.setBookId(bookId);
        checkout.setCheckoutDate(LocalDate.now().toString());
        checkout.setReturnDate(LocalDate.now().plusDays(7).toString());
        checkout.setBookId(book.get().getId());
        checkoutRepository.save(checkout);

        return book.get();
    }

    public boolean checkoutBookByUser(String email, Long bookId) {
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(email, bookId);
        return validateCheckout != null;
    }

    public int currentLoansCount(String email) {
        List<Checkout> checkouts = checkoutRepository.findByUserEmail(email);
        return checkouts.size();
    }
}

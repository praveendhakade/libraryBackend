package com.library.contollers;

import com.library.models.Book;
import com.library.models.CheckoutRequest;
import com.library.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CheckoutController {
    @Autowired
    private  CheckoutService checkoutService;

    @GetMapping(path = "checkout/checkoutCount/{email}")
    public Integer checkoutBookCount(@PathVariable(name = "email") String email)  {
        return checkoutService.currentLoansCount(email);
    }

    @PutMapping(path = "checkout")
    public Book checkoutBook(@RequestBody CheckoutRequest checkoutRequest) throws Exception {
        return checkoutService.checkoutBook(checkoutRequest.getEmail(), checkoutRequest.getBookId());
    }

    @GetMapping(path = "checkout/isCheckedoutByUser")
    public boolean isCheckedOutByUser(@RequestParam(name = "email") String email, @RequestParam(name = "bookId") Long bookId) {
        return checkoutService.checkoutBookByUser(email, bookId);
    }
}

package dev.raffaele.financetrackerultimate.controller;

import dev.raffaele.financetrackerultimate.model.TransactionModel;
import dev.raffaele.financetrackerultimate.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions/")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionModel> createTransaction(@RequestBody TransactionModel transactionModel){
        return ResponseEntity.ok(transactionService.createTransaction(transactionModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionModel> getUserById(@PathVariable int id){
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionModel>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionModel> updateTransaction(@PathVariable int id, @RequestBody TransactionModel transactionModel){
        return ResponseEntity.ok(transactionService.updateTransaction(id, transactionModel));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

}

package dev.raffaele.financetrackerultimate.service;

import dev.raffaele.financetrackerultimate.model.TransactionModel;

import java.util.List;

public interface TransactionService {
     TransactionModel createTransaction(TransactionModel transactionModel);
     TransactionModel getTransactionById(int id);
     List<TransactionModel> getAllTransactions();
     TransactionModel updateTransaction(int id, TransactionModel transactionModel);
     void deleteTransaction(int id);
}

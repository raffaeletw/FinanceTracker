package dev.raffaele.financetrackerultimate.service;

import dev.raffaele.financetrackerultimate.entity.TransactionEntity;
import dev.raffaele.financetrackerultimate.entity.UserEntity;
import dev.raffaele.financetrackerultimate.exception.TransactionNotFoundException;
import dev.raffaele.financetrackerultimate.model.TransactionModel;
import dev.raffaele.financetrackerultimate.repository.TransactionRepository;
import dev.raffaele.financetrackerultimate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  TransactionServiceImpl implements TransactionService{

        private final TransactionRepository transactionRepository;
        private final UserRepository userRepository;

        @Autowired
        public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
            this.transactionRepository = transactionRepository;
            this.userRepository = userRepository;
        }

        @Override
        public TransactionModel createTransaction(TransactionModel transactionModel) {
            TransactionEntity transactionEntity = mapToEntity(transactionModel);
            TransactionEntity savedTransaction = transactionRepository.save(transactionEntity);
            updateUserBalance(transactionEntity.getUser());
            return mapToModel(savedTransaction);
        }

        @Override
        public TransactionModel getTransactionById(int id) {
            TransactionEntity transactionEntity = transactionRepository.findById(id)
                    .orElseThrow(TransactionNotFoundException::new);
            return mapToModel(transactionEntity);
        }

        @Override
        public List<TransactionModel> getAllTransactions() {
            return transactionRepository.findAll()
                    .stream()
                    .map(this::mapToModel)
                    .collect(Collectors.toList());
        }

        @Override
        public TransactionModel updateTransaction(int id, TransactionModel transactionModel) {
            Optional<TransactionEntity> transactionEntityOptional = transactionRepository.findById(id);
            if (transactionEntityOptional.isEmpty()) {
                throw new TransactionNotFoundException();
            }
            TransactionEntity transactionEntity = transactionEntityOptional.get();
            transactionEntity.setType(transactionModel.getType());
            transactionEntity.setAmount(transactionModel.getAmount());
            transactionEntity.setCreatedOn(transactionModel.getCreatedOn());
            transactionEntity.setCategory(transactionModel.getCategory());
            transactionEntity.setUser(transactionModel.getUser());

            TransactionEntity updatedTransactionEntity = transactionRepository.save(transactionEntity);
            updateUserBalance(transactionEntity.getUser());
            return mapToModel(updatedTransactionEntity);
        }

        @Override
        public void deleteTransaction(int id) {
            Optional<TransactionEntity> transactionEntityOptional = transactionRepository.findById(id);
            if (transactionEntityOptional.isEmpty()) {
                throw new TransactionNotFoundException();
            }
            TransactionEntity transactionEntity = transactionEntityOptional.get();
            transactionRepository.delete(transactionEntity);
            updateUserBalance(transactionEntity.getUser());
        }

    private void updateUserBalance(UserEntity user) {
        if (user != null) {
            UserEntity existingUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            double totalIncome = userRepository.calculateUserIncome(existingUser.getId());
            double totalExpenses = userRepository.calculateUserExpenses(existingUser.getId());
            double totalWithdrawals = userRepository.calculateUserWithdrawals(existingUser.getId());

            existingUser.setBalance( totalIncome - totalExpenses - totalWithdrawals);

            userRepository.save(existingUser);
        }
    }


        private TransactionModel mapToModel(TransactionEntity transactionEntity) {
            return new TransactionModel(
                    transactionEntity.getId(),
                    transactionEntity.getAmount(),
                    transactionEntity.getType(),
                    transactionEntity.getCategory(),
                    transactionEntity.getCreatedOn(),
                    transactionEntity.getUser()
            );
        }

        private TransactionEntity mapToEntity(TransactionModel transactionModel) {
            return new TransactionEntity(
                    transactionModel.getId(),
                    transactionModel.getAmount(),
                    transactionModel.getType(),
                    transactionModel.getCategory(),
                    transactionModel.getCreatedOn(),
                    transactionModel.getUser()
            );
        }
    }

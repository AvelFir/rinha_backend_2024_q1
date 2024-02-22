package br.com.guilherme.rinhabackend2024q1.services;

import br.com.guilherme.rinhabackend2024q1.dtos.TransactionDto;
import br.com.guilherme.rinhabackend2024q1.entities.TransactionEntity;
import br.com.guilherme.rinhabackend2024q1.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    public TransactionRepository transactionRepository;

    @Transactional
    public void saveTransaction(TransactionEntity transactionEntity){
        transactionRepository.save(transactionEntity);
    }

    public List<TransactionEntity> getLast10Transactions(Integer clientId) {
        return transactionRepository.findLastNTransactionsByClientId(clientId, PageRequest.of(0, 10));
    }
}

package com.sgu.banksspring.model;

import com.sgu.banksspring.entity.Operation;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OperationModel extends CrudRepository<Operation, Long> {
    List<Operation> findByAccountFrom(Long accountFrom);
}
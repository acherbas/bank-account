package com.bank.account.repositories;

import com.bank.account.model.AccountOp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountOpRepository extends JpaRepository<AccountOp, Long> {
}

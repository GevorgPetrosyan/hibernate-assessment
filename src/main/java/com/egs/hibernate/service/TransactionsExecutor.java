package com.egs.hibernate.service;

import com.egs.hibernate.entity.User;

public interface TransactionsExecutor {

    User saveUser(String username);
}

package com.github.kropsz.bankapi.repository

import com.github.kropsz.bankapi.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
}
package com.github.kropsz.bankapi.service

import com.github.kropsz.bankapi.entity.Account
import com.github.kropsz.bankapi.repository.AccountRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl(private val repository: AccountRepository) : AccountService {
    override fun create(account: Account): Account {
        return repository.save(account)
    }

    override fun getAll(): List<Account> {
        return repository.findAll()
    }

    override fun getById(id: Long): Optional<Account> {
        return repository.findById(id)
    }

    override fun update(id: Long, account: Account): Optional<Account> {
        val optional = getById(id)
        if (optional.isEmpty)Optional.empty<Account>()

        return optional.map {
            val accountUpdate = it.copy(
                name = account.name,
                document = account.document,
                phone = account.phone
            )
            (repository.save(accountUpdate))
        }
    }

    override fun delete(id: Long) {
        repository.findById(id).map {
            repository.delete(it)
        }.orElseThrow{ throw RuntimeException("Id not found")}
    }


}
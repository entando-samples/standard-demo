package org.entando.demo.banking.service.creditCardTransaction.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link org.entando.demo.banking.domain.CreditcardTransaction} entity. This class is used
 * in {@link org.entando.demo.banking.web.rest.CreditcardTransactionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /creditcardtransactions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CreditcardTransactionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter date;

    private StringFilter description;

    private BigDecimalFilter amount;

    private BigDecimalFilter balance;

    private LongFilter accountID;

    public CreditcardTransactionCriteria(){
    }

    public CreditcardTransactionCriteria(CreditcardTransactionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.balance = other.balance == null ? null : other.balance.copy();
        this.accountID = other.accountID == null ? null : other.accountID.copy();
    }

    @Override
    public CreditcardTransactionCriteria copy() {
        return new CreditcardTransactionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public BigDecimalFilter getAmount() {
        return amount;
    }

    public void setAmount(BigDecimalFilter amount) {
        this.amount = amount;
    }

    public BigDecimalFilter getBalance() {
        return balance;
    }

    public void setBalance(BigDecimalFilter balance) {
        this.balance = balance;
    }

    public LongFilter getAccountID() {
        return accountID;
    }

    public void setAccountID(LongFilter accountID) {
        this.accountID = accountID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CreditcardTransactionCriteria that = (CreditcardTransactionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(date, that.date) &&
            Objects.equals(description, that.description) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(balance, that.balance) &&
            Objects.equals(accountID, that.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        date,
        description,
        amount,
        balance,
        accountID
        );
    }

    @Override
    public String toString() {
        return "CreditcardtransactionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (balance != null ? "balance=" + balance + ", " : "") +
                (accountID != null ? "accountID=" + accountID + ", " : "") +
            "}";
    }

}

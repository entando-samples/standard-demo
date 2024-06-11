package org.entando.demo.banking.service.savings.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.entando.domain.Savings} entity. This class is used
 * in {@link com.entando.web.rest.SavingsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /savings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SavingsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter accountNumber;

    private BigDecimalFilter balance;

    private StringFilter userID;

    public SavingsCriteria(){
    }

    public SavingsCriteria(SavingsCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.accountNumber = other.accountNumber == null ? null : other.accountNumber.copy();
        this.balance = other.balance == null ? null : other.balance.copy();
        this.userID = other.userID == null ? null : other.userID.copy();
    }

    @Override
    public SavingsCriteria copy() {
        return new SavingsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(StringFilter accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimalFilter getBalance() {
        return balance;
    }

    public void setBalance(BigDecimalFilter balance) {
        this.balance = balance;
    }

    public StringFilter getUserID() {
        return userID;
    }

    public void setUserID(StringFilter userID) {
        this.userID = userID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SavingsCriteria that = (SavingsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(accountNumber, that.accountNumber) &&
            Objects.equals(balance, that.balance) &&
            Objects.equals(userID, that.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        accountNumber,
        balance,
        userID
        );
    }

    @Override
    public String toString() {
        return "SavingsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (accountNumber != null ? "accountNumber=" + accountNumber + ", " : "") +
                (balance != null ? "balance=" + balance + ", " : "") +
                (userID != null ? "userID=" + userID + ", " : "") +
            "}";
    }

}

package org.entando.demo.banking.service.creditcard.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.entando.domain.Creditcard} entity. This class is used
 * in {@link com.entando.web.rest.CreditcardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /creditcards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CreditcardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter accountNumber;

    private BigDecimalFilter balance;

    private LongFilter rewardPoints;

    private StringFilter userID;

    public CreditcardCriteria(){
    }

    public CreditcardCriteria(CreditcardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.accountNumber = other.accountNumber == null ? null : other.accountNumber.copy();
        this.balance = other.balance == null ? null : other.balance.copy();
        this.rewardPoints = other.rewardPoints == null ? null : other.rewardPoints.copy();
        this.userID = other.userID == null ? null : other.userID.copy();
    }

    @Override
    public CreditcardCriteria copy() {
        return new CreditcardCriteria(this);
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

    public LongFilter getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(LongFilter rewardPoints) {
        this.rewardPoints = rewardPoints;
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
        final CreditcardCriteria that = (CreditcardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(accountNumber, that.accountNumber) &&
            Objects.equals(balance, that.balance) &&
            Objects.equals(rewardPoints, that.rewardPoints) &&
            Objects.equals(userID, that.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        accountNumber,
        balance,
        rewardPoints,
        userID
        );
    }

    @Override
    public String toString() {
        return "CreditcardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (accountNumber != null ? "accountNumber=" + accountNumber + ", " : "") +
                (balance != null ? "balance=" + balance + ", " : "") +
                (rewardPoints != null ? "rewardPoints=" + rewardPoints + ", " : "") +
                (userID != null ? "userID=" + userID + ", " : "") +
            "}";
    }

}

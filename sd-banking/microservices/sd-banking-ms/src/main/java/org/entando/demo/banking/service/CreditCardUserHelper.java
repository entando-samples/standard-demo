package org.entando.demo.banking.service;

import org.entando.demo.banking.domain.CreditCardUser;
import org.entando.demo.banking.service.dto.CreditCardUserPost;
import org.springframework.stereotype.Component;

@Component
public class CreditCardUserHelper {

    /**
     * This method can be used to convert a dto into a CreditCardUser entity
     *
     * @param dto
     * @return
     */
    public static CreditCardUser convert(CreditCardUserPost dto) {
        CreditCardUser user = new CreditCardUser();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(dto.getCreatedAt());
        user.setStatus(dto.getStatus());
        return user;
    }
}

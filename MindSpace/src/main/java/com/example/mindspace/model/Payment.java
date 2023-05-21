package com.example.mindspace.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment extends AbstractEntity {

    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private boolean unpaid;

}

package com.pedro.bills.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Titulo {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Descrição é obrigatória")
    @Size(max=60, message = "Descrção não pode ter mais de 60 caracteres")
    private String descricao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Data não pode ser em branco")
    private Date dataVencimento;

    @NotNull(message = "Valor é obrigatório")
    @NumberFormat(pattern = "#,##0.00")
    @DecimalMin(value= "0.01")
    @DecimalMax(value= "9999999.99")
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusTitulo status;

    public boolean isPendente(){
        return StatusTitulo.PENDENTE.equals(this.status);
    }
}

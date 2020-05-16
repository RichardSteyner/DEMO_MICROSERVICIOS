package com.codifacil.serviceshopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import com.codifacil.serviceshopping.modelo.Product;

import lombok.Data;

@Entity
@Data
@Table(name = "invoce_item")
public class InvoiceItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Positive(message = "Cantidad debe ser mayor que cero")
    private Double quantity;
    private Double  price;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private Product product;

    @Transient //para que no se guarde en la BD
    private Double subTotal;

    public Double getSubTotal(){
        if (this.price >0  && this.quantity >0 ){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }
    
    public InvoiceItem(){
        this.quantity=(double) 0;
        this.price=(double) 0;

    }

}

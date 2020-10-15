package com.turing.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Orders implements Serializable {
    private Integer odrId;

    private String odrCustomer;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date odrDate;

    private String odrAddr;

    private String odrStatus;
    //¶©µ¥×Ü¼Û
    private Double totalPrice;

    public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	private static final long serialVersionUID = 1L;

    public Integer getOdrId() {
        return odrId;
    }

    public void setOdrId(Integer odrId) {
        this.odrId = odrId;
    }

    public String getOdrCustomer() {
        return odrCustomer;
    }

    public void setOdrCustomer(String odrCustomer) {
        this.odrCustomer = odrCustomer == null ? null : odrCustomer.trim();
    }

    public Date getOdrDate() {
        return odrDate;
    }

    public void setOdrDate(Date odrDate) {
        this.odrDate = odrDate;
    }

    public String getOdrAddr() {
        return odrAddr;
    }

    public void setOdrAddr(String odrAddr) {
        this.odrAddr = odrAddr == null ? null : odrAddr.trim();
    }

    public String getOdrStatus() {
        return odrStatus;
    }

    public void setOdrStatus(String odrStatus) {
        this.odrStatus = odrStatus == null ? null : odrStatus.trim();
    }
}
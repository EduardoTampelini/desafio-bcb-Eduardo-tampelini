package com.bcb.bigchatbrasil.DTO;

import com.bcb.bigchatbrasil.Modals.FinClient;

import java.math.BigDecimal;

public record PlanData(Integer limitcred, Integer cred, BigDecimal value) {

    public PlanData(FinClient fincli){
    this(fincli.getLimitCredit(), fincli.getCredit(), fincli.getValuePlan());}
}

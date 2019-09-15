package com.alltrustnetworks.smartcheck.models.consumer;

public class TemporaryRequest {

    public String cell_phone;

    public TemporaryRequest(String cellPhone) {
        this.cell_phone = cellPhone;
    }


    public String getCellPhone() {
        return cell_phone;
    }

    public void setCellPhone(String cellPhone) {
        this.cell_phone = cellPhone;
    }
}

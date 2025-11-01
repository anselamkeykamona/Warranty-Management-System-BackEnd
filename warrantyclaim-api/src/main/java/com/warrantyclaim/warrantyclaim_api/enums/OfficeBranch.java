package com.warrantyclaim.warrantyclaim_api.enums;

public enum OfficeBranch {

    HANOI("VinFast Hanoi Head Office", "8 Lang Ha Street, Dong Da District, Hanoi"),
    HAIPHONG("VinFast Hai Phong Manufacturing Complex", "Dinh Vu - Cat Hai Economic Zone, Cat Hai District, Hai Phong"),
    HCM("VinFast Ho Chi Minh City Office", "29 Nguyen Thi Minh Khai, District 1, Ho Chi Minh City"),
    DA_NANG("VinFast Da Nang Regional Branch", "120 Nguyen Van Linh, Hai Chau District, Da Nang"),
    CAN_THO("VinFast Can Tho Service Center", "23 Vo Van Kiet Street, Ninh Kieu District, Can Tho");

    private final String branchName;
    private final String address;

    OfficeBranch(String branchName, String address) {
        this.branchName = branchName;
        this.address = address;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getAddress() {
        return address;
    }
}

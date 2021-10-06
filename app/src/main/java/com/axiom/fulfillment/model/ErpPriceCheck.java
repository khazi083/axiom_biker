package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErpPriceCheck {

//        "AomsOrganizationCode": "sample string 1",
//                "AclmChannelCode": "sample string 2",
//                "AlhrHierarchyCode": 1.0,
//                "AlnmLocationCode": "sample string 3",
//                "ItmrItemCode": "sample string 4"

    @SerializedName("AomsOrganizationCode")
    @Expose
    private String AomsOrganizationCode;
    @SerializedName("AclmChannelCode")
    @Expose
    private String AclmChannelCode;

    @SerializedName("AlnmLocationCode")
    @Expose
    private String AlnmLocationCode;
    @SerializedName("ItmrItemCode")
    @Expose
    private String ItmrItemCode;

    public String getAomsOrganizationCode() {
        return AomsOrganizationCode;
    }

    public void setAomsOrganizationCode(String aomsOrganizationCode) {
        AomsOrganizationCode = aomsOrganizationCode;
    }

    public String getAclmChannelCode() {
        return AclmChannelCode;
    }

    public void setAclmChannelCode(String aclmChannelCode) {
        AclmChannelCode = aclmChannelCode;
    }

    public String getAlnmLocationCode() {
        return AlnmLocationCode;
    }

    public void setAlnmLocationCode(String alnmLocationCode) {
        AlnmLocationCode = alnmLocationCode;
    }

    public String getItmrItemCode() {
        return ItmrItemCode;
    }

    public void setItmrItemCode(String itmrItemCode) {
        ItmrItemCode = itmrItemCode;
    }
}

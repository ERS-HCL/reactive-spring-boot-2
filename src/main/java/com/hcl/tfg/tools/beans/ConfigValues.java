package com.hcl.tfg.tools.beans;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
@Table("cloud_key_value_data")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigValues {


    private String groupid;

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }


    public String getConfigname() {
        return configname;
    }

    public void setConfigname(String configname) {
        this.configname = configname;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public String getValuetype() {
        return valuetype;
    }

    public void setValuetype(String valuetype) {
        this.valuetype = valuetype;
    }

    public String getKeyvalue() {
        return keyvalue;
    }

    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getIncludedgeo() {
        return includedgeo;
    }

    public void setIncludedgeo(String includedgeo) {
        this.includedgeo = includedgeo;
    }

    public String getExcludedgeo() {
        return excludedgeo;
    }

    public void setExcludedgeo(String excludedgeo) {
        this.excludedgeo = excludedgeo;
    }

    public String getBrowsingdevicetype() {
        return browsingdevicetype;
    }

    public void setBrowsingdevicetype(String browsingdevicetype) {
        this.browsingdevicetype = browsingdevicetype;
    }

    public String getIncludedflows() {
        return includedflows;
    }

    public void setIncludedflows(String includedflows) {
        this.includedflows = includedflows;
    }

    public String getIncludedsubflows() {
        return includedsubflows;
    }

    public void setIncludedsubflows(String includedsubflows) {
        this.includedsubflows = includedsubflows;
    }

    public String getExcludedsubflows() {
        return excludedsubflows;
    }

    public void setExcludedsubflows(String excludedsubflows) {
        this.excludedsubflows = excludedsubflows;
    }

    public String getIncludedcommimenterms() {
        return includedcommimenterms;
    }

    public void setIncludedcommimenterms(String includedcommimenterms) {
        this.includedcommimenterms = includedcommimenterms;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    private String groupname;
    private String category;
    private String subcategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @PrimaryKey()
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String configname;
    private String application;
    private String keyname;
    private String valuetype;
    private String keyvalue;
    private String customertype;
    private String includedgeo;
    private String excludedgeo;
    private String browsingdevicetype;
    private String includedflows;
    private String includedsubflows;
    private String excludedsubflows;
    private String includedcommimenterms;
    private String startdate;
    private String enddate;

}

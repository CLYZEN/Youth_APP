package com.example.youthapp.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.youthapp.PolicyModel.Emp;

import java.util.ArrayList;

@Entity
public class Policy {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "policy_title")
    public String policyTitle;

    @ColumnInfo(name = "policy_id")
    public String policyId;

    @ColumnInfo(name = "policy_content")
    public String policyContent;

    @ColumnInfo(name = "policy_link")
    public String policyLink;

}

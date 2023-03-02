package com.db.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WifiDto {
	
	private String distance;      //0 select과 하버사인 공식을 통한 콜라보로 만들어내기
	private String merNo;          //1
	private String gu;             //2	
	private String wifiName;       //3
	private String arr1;           //4
    private String arr2;           //5
	private String floor;          //6
	private String installType;    //7
	private String installAgency;  //8
	private String service;        //9
	private String mangType;       //10
	private String installYear;    //11 
	private String where;          //12
	private String wifiState;      //13
	private String lat;            //14
	private String lnt;            //15
	private String installDate;
}

package com.db.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {

		private String id;
		private String lat;
		private String lnt;
		private String datetime;


}

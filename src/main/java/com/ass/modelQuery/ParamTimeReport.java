package com.ass.modelQuery;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamTimeReport {
	@Temporal(TemporalType.DATE)
	private Date dateFrom = new Date();
	@Temporal(TemporalType.DATE)
	private Date dateEnd = new Date();
}

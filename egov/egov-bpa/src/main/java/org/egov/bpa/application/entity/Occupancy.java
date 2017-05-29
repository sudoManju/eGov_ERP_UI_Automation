/**
 * eGov suite of products aim to improve the internal efficiency,transparency,
   accountability and the service delivery of the government  organizations.
    Copyright (C) <2015>  eGovernments Foundation
    The updated version of eGov suite of products as by eGovernments Foundation
    is available at http://www.egovernments.org
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program. If not, see http://www.gnu.org/licenses/ or
    http://www.gnu.org/licenses/gpl.html .
    In addition to the terms of the GPL license to be adhered to in using this
    program, the following additional terms are to be complied with:
        1) All versions of this program, verbatim or modified must carry this
           Legal Notice.
        2) Any misrepresentation of the origin of the material is prohibited. It
           is required that all modified versions of this material be marked in
           reasonable ways as different from the original version.
        3) This license does not grant any rights to any user of the program
           with regards to rights under trademark law for use of the trade names
           or trademarks of eGovernments Foundation.
  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.bpa.application.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.egov.infra.persistence.entity.AbstractAuditable;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "EGBPA_MSTR_OCCUPANCY")
@SequenceGenerator(name = Occupancy.SEQ_OCCUPANCY, sequenceName = Occupancy.SEQ_OCCUPANCY, allocationSize = 1)
public class Occupancy extends AbstractAuditable {

    private static final long serialVersionUID = 3078684328383202788L;
    public static final String SEQ_OCCUPANCY = "SEQ_EGBPA_MSTR_OCCUPANCY";
    @Id
    @GeneratedValue(generator = SEQ_OCCUPANCY, strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Length(min = 1, max = 128)
    @Column(name = "code", unique = true)
    private String code;
    @NotNull
    @Length(min = 1, max = 256)
    private String description;
    private Boolean isactive;
  
    private BigDecimal occupantDoors;
    
    private BigDecimal noofOccupancy;
    
    private BigDecimal occupantLoad;
    
    private Double permissibleAreaInPercentage;
    private Double numOfTimesAreaPermissible;
    private Double numOfTimesAreaPermWitAddnlFee;
    private Integer orderNumber;
    
    public Double getPermissibleAreaInPercentage() {
        return permissibleAreaInPercentage;
    }

    public void setPermissibleAreaInPercentage(Double permissibleAreaInPercentage) {
        this.permissibleAreaInPercentage = permissibleAreaInPercentage;
    }

    public Double getNumOfTimesAreaPermissible() {
        return numOfTimesAreaPermissible;
    }

    public void setNumOfTimesAreaPermissible(Double numOfTimesAreaPermissible) {
        this.numOfTimesAreaPermissible = numOfTimesAreaPermissible;
    }

    public Double getNumOfTimesAreaPermWitAddnlFee() {
        return numOfTimesAreaPermWitAddnlFee;
    }

    public void setNumOfTimesAreaPermWitAddnlFee(Double numOfTimesAreaPermWitAddnlFee) {
        this.numOfTimesAreaPermWitAddnlFee = numOfTimesAreaPermWitAddnlFee;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public BigDecimal getOccupantDoors() {
		return occupantDoors;
	}

	public void setOccupantDoors(BigDecimal occupantDoors) {
		this.occupantDoors = occupantDoors;
	}

	public BigDecimal getNoofOccupancy() {
		return noofOccupancy;
	}

	public void setNoofOccupancy(BigDecimal noofOccupancy) {
		this.noofOccupancy = noofOccupancy;
	}

	public BigDecimal getOccupantLoad() {
		return occupantLoad;
	}

	public void setOccupantLoad(BigDecimal occupantLoad) {
		this.occupantLoad = occupantLoad;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
}
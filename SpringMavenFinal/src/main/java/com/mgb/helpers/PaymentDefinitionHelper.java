package com.mgb.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mgb.bo.PaymentDefinition;
import com.mgb.forms.PaymentDefDTO;
import com.mgb.utils.ApplicationUtils;

@Component
public class PaymentDefinitionHelper {

	public PaymentDefinition convertToBo(PaymentDefDTO paymentDefDTO) {
		PaymentDefinition bo =new PaymentDefinition();
		if(paymentDefDTO!=null){
			bo.setId(paymentDefDTO.getId());
			bo.setMonth(Integer.parseInt(paymentDefDTO.getDefMonth()));
			bo.setYear(Integer.parseInt(paymentDefDTO.getDefYear()));
			bo.setAmount(Integer.parseInt(paymentDefDTO.getAmount()));
			bo.setIsActive(true);
		}
		return bo;
	}

	public PaymentDefDTO convertToDTO(PaymentDefinition bo) {
		PaymentDefDTO dto=new PaymentDefDTO();
		if(bo!=null){
			dto.setId(bo.getId());
			dto.setDefMonth(String.valueOf(bo.getMonth()));
			dto.setDefYear(String.valueOf(bo.getYear()));
			dto.setAmount(String.valueOf(bo.getAmount()));
		}
		return dto;
	}

	public List<PaymentDefDTO> convertToDTOList(List<PaymentDefinition> boList) {
		List<PaymentDefDTO> dtoList=new ArrayList<PaymentDefDTO>();
		for(PaymentDefinition bo :boList){
			PaymentDefDTO dto=new PaymentDefDTO();
			dto.setId(bo.getId());
			dto.setDefMonth(ApplicationUtils.monthsArr[bo.getMonth()]);
			dto.setDefYear(String.valueOf(bo.getYear()));
			dto.setAmount(String.valueOf(bo.getAmount()));
			dtoList.add(dto);
		}
		return dtoList;
	}

}

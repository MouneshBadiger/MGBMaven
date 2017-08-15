package com.mgb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mgb.forms.PaymentDetailsDTO;
import com.mgb.forms.PaymentMonthlyDetailsDto;
import com.mgb.services.PaymentService;

@Controller()
@SessionAttributes("paymentDto")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@RequestMapping("admin/openPaymentPage")
	
	public String showMonthlyPaymentPage(Model model,@RequestParam(value = "userId", required = false) String userId){
		PaymentDetailsDTO paymentDto;
		try {
			paymentDto = paymentService.getPaymentDetailsDTO(userId);
			List<PaymentMonthlyDetailsDto> monthlyDetailsList=paymentService.getMonthlyDetailsList(userId);
			paymentDto.setMonthDetailsList(monthlyDetailsList);
			model.addAttribute("paymentDto",paymentDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "openPayment";
	}
	@RequestMapping("admin/addMonthlyPayment")
	public String addMonthlyPayment(Model model,@ModelAttribute("paymentDto") PaymentDetailsDTO paymentDto){
		try {
			boolean isPaymentAdded=paymentService.addMonthlyPayment(paymentDto);
			paymentDto = paymentService.getPaymentDetailsDTO(paymentDto.getUserId());
			List<PaymentMonthlyDetailsDto> monthlyDetailsList=paymentService.getMonthlyDetailsList(paymentDto.getUserId());
			paymentDto.setMonthDetailsList(monthlyDetailsList);
			model.addAttribute("paymentDto",paymentDto);
			if(isPaymentAdded){
				model.addAttribute("message","Payment added Successfully");
			}else{
				model.addAttribute("error","Fialed to add payment");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error","Fialed to add payment");
		}
		return "openPayment";
	}
	@RequestMapping("admin/cancelMonthPayment")
	public String cancelMonthPayment(Model model,@ModelAttribute("paymentDto") PaymentDetailsDTO paymentDto,@RequestParam(value = "boId", required = false) String boId){
		try {
			boolean isPaymentCancelled=paymentService.cancelMonthlyPayment(boId);
			paymentDto = paymentService.getPaymentDetailsDTO(paymentDto.getUserId());
			List<PaymentMonthlyDetailsDto> monthlyDetailsList=paymentService.getMonthlyDetailsList(paymentDto.getUserId());
			paymentDto.setMonthDetailsList(monthlyDetailsList);
			model.addAttribute("paymentDto",paymentDto);
			if(isPaymentCancelled){
				model.addAttribute("message","Payment cancelled Successfully");
			}else{
				model.addAttribute("error","Fialed to cancell payment");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error","Fialed to cancell payment");
		}
		return "openPayment";
	}

}

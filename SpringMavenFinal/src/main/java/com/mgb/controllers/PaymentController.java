package com.mgb.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mgb.bo.PaymentDefinition;
import com.mgb.forms.AreaMasterDto;
import com.mgb.forms.PaymentDefDTO;
import com.mgb.forms.PaymentDetailsDTO;
import com.mgb.forms.PaymentMonthlyDetailsDto;
import com.mgb.services.PaymentService;
import com.mgb.to.YearWisePaymentDto;

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
	@RequestMapping("admin/openPaymentDefPage")
	public String listAllPaymentDef(Model model){
		try {
			List<PaymentDefDTO> defList=paymentService.listAllPaymentDefinition();
			model.addAttribute("defList", defList);
			PaymentDefDTO paymentDefDTO=new PaymentDefDTO();
			model.addAttribute("paymentDefDTO", paymentDefDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "openPaymentDef";
	}

	@RequestMapping("admin/paymentDef/add")
	public String addPaymentDef(Model model,@Valid @ModelAttribute("paymentDefDTO") PaymentDefDTO paymentDefDTO,BindingResult result){
		try {
			if(!result.hasErrors()){
				boolean isDuplicate=false;
				if(paymentDefDTO.getId()==0){
					isDuplicate=paymentService.checkForDuplicate(paymentDefDTO);			
				}					
				if(!isDuplicate){
					boolean isDefAdded=paymentService.addOrUpdatePaymentDef(paymentDefDTO);
					if(isDefAdded){
						model.addAttribute("message","Payment Definition Successfully");
					}else{
						model.addAttribute("error","Fialed to add Payment Definition");
					}
				}else{
					model.addAttribute("error","Sorry this entry already exists.");
				}
			}
			
			
			
			List<PaymentDefDTO> defList=paymentService.listAllPaymentDefinition();
			model.addAttribute("defList", defList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "openPaymentDef";
	}

	@RequestMapping("admin/paymentDef/edit")
	public String editPaymentDef(Model model,@RequestParam(value = "boId", required = false) String boId){
		try {
			PaymentDefDTO paymentDefDTO=paymentService.editPaymentDef(boId);
			model.addAttribute("paymentDefDTO", paymentDefDTO);
			List<PaymentDefDTO> defList=paymentService.listAllPaymentDefinition();
			model.addAttribute("defList", defList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "openPaymentDef";
	}

	@RequestMapping("admin/paymentDef/delete")
	public String deletePaymentDef(Model model,@ModelAttribute("paymentDefDTO") PaymentDefDTO paymentDefDTO,@RequestParam(value = "boId", required = false) String boId){
		try {
			boolean isPaymentDefDeleted=paymentService.deletePaymentDef(boId);
			if(isPaymentDefDeleted){
				model.addAttribute("message","Payment Definition deleted Successfully");
			}else{
				model.addAttribute("error","Fialed to delete Payment Definition");
			}
			List<PaymentDefDTO> defList=paymentService.listAllPaymentDefinition();
			model.addAttribute("defList", defList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "openPaymentDef";
	}
	@RequestMapping("admin/calculateOnAmount")
	public void calculateOnAmount(Model model,@ModelAttribute("paymentDto") PaymentDetailsDTO paymentDto,HttpServletRequest req,HttpServletResponse resp,@RequestParam(value = "amountG", required = false) Integer amountG){
		try {
			int defTillId=0;
			Map<Integer,PaymentDefinition> paymentDefMap=paymentService.getPaymentDefinitionMap();
			List<String> keyList = new ArrayList<String>(paymentDto.getPendingTillMonthMap().keySet());
			for (int i=keyList.size()-1;i>=0;i--) {
				 defTillId=Integer.parseInt(keyList.get(i));
				PaymentDefinition def=paymentDefMap.get(defTillId);
				if(amountG>def.getAmount()){
					amountG=amountG-def.getAmount();
				}else{
					break;
				}
			}
			String respStr=amountG+"_"+defTillId;
			resp.getWriter().write(respStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/admin/paymentYearDetails")
	public String getPaymentYearDetails(Model model,@ModelAttribute("areaMasterDto") AreaMasterDto areaMasterDto,@RequestParam(value = "areaId",required = false) String areaId,@RequestParam(value = "printYearRep",required = false) String printYearRep){
		try {
				List<YearWisePaymentDto> subsList=paymentService.getYearwisePaymentDetails(Calendar.getInstance().get(Calendar.YEAR),areaId);
				model.addAttribute("subsList",subsList);
				areaMasterDto.setName(areaId);
				model.addAttribute("areaMasterDto",areaMasterDto);
				Map<Integer,String> areaMap=paymentService.getAreaMap();
				model.addAttribute("areaMap",areaMap);
				if(areaId!=null && !areaId.isEmpty() && !areaId.equals("0")){
					model.addAttribute("areaName", areaMap.get(Integer.parseInt(areaId)));
					model.addAttribute("areaId",areaId);
				}
				else{
					model.addAttribute("areaName", "All");
					model.addAttribute("areaId","0");
				}
				if(printYearRep!=null && printYearRep.equals("true")){
					return "printYearReport";
				}else{
					return "userInfoYearView";
				}
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}


}

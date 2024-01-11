package com.demoapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demoapi.model.TrxType;
import com.demoapi.repository.TrxTypeRepository;


@RestController
@RequestMapping("/api/trxType")
public class TrxTypeController {

	@Autowired
	private TrxTypeRepository trxTypeRepository;
	
	//add trx type
	@PostMapping(value = "/addTrxType", produces = "application/json", consumes = "application/json")
	@ResponseBody
	public TrxType addTrxType(@Valid @RequestBody TrxType trxType) {

		return trxTypeRepository.save(trxType);
	}
}

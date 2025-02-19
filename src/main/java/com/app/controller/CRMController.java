package com.app.controller;

import com.app.entity.evaluation.Agent;
import com.app.entity.evaluation.Area;
import com.app.entity.evaluation.CustomerVisit;
import com.app.repository.AgentRepository;
import com.app.repository.AreaRepository;
import com.app.repository.CustomerVisitRepository;
import com.app.service.SmsService;
import com.app.service.WhatsAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crm")
public class CRMController {
    private AreaRepository areaRepository;
    private AgentRepository agentRepository;
    private CustomerVisitRepository customerVisitRepository;
    private SmsService smsService;
    private WhatsAppService whatsAppService ;  // service for sending whatsapp message

    public CRMController(AreaRepository areaRepository, AgentRepository agentRepository, CustomerVisitRepository customerVisitRepository, SmsService smsService, WhatsAppService whatsAppService) {
        this.areaRepository = areaRepository;
        this.agentRepository = agentRepository;
        this.customerVisitRepository = customerVisitRepository;
        this.smsService = smsService;

        this.whatsAppService = whatsAppService;
    }

    @GetMapping
    public ResponseEntity<List<Area>> searchAgent(
            @RequestParam int pinCode
    ) {
        List<Area> areas = areaRepository.findByPinCode(pinCode);
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    //update the agent details in database
    @PutMapping
    public String allocateAgent(
            @RequestParam long customerId,
            @RequestParam long agentId
    ) {

        Agent agent = null;
        Optional<Agent> opAgent = agentRepository.findById(agentId);
        if(opAgent.isPresent()) {
            agent = opAgent.get();

        }
        CustomerVisit customerVisit = customerVisitRepository.findById(customerId).get();
       customerVisit.setAgent(agent);

       customerVisitRepository.save(customerVisit);
       //SMS
        smsService.sendSms("+919701181517","Agent is now Allocated - Love U  Maa");
       //WhatsAppMessage
        whatsAppService.sendWhatsAppMessage("+919701181517", "Agent is now Allocated - Hello Darling");


        return "Agent is now Allocated";

    }

}

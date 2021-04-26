package com.api.calculator.controller;

import com.api.calculator.model.UserOperation;
import com.api.calculator.service.IUserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.api.calculator.utilities.GeneralConstants.*;

@Controller
public class AuditController {

    @Autowired
    private IUserOperationService userOperationService;

    @GetMapping("/audit")
    public String generateToken(Model model) {
        List<UserOperation> userOperationAuditList = userOperationService.findAllUserOperation();
        model.addAttribute(AUDIT_ATTRIBUTE, userOperationAuditList);
        return AUDIT_PAGE;
    }
}

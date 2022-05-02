package com.mycompany.invoise.invoiseweb.controller;


import com.mycompany.invoise.core.model.Invoice;
import com.mycompany.invoise.core.service.IInvoiceService;
import com.mycompany.invoise.invoiseweb.form.InvoiceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/invoice")
public class InvoiceControllerWeb {

    @Autowired
    private IInvoiceService invoiceService;

    @PostMapping
    public String createInvoice(@Valid @ModelAttribute InvoiceForm invoiceForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "invoice-create-form";
        }

        Invoice invoice = new Invoice();
        invoice.setCustomerName(invoiceForm.getCustomerName());
        invoice.setOrderNumber(invoiceForm.getOrderNumber());

        invoiceService.createInvoice(invoice);

        return "invoice-created";
    }

    @GetMapping("/home")
    public String displayHome(Model model) {

        model.addAttribute("invoices", invoiceService.getInvoiceList());

        return "invoice-home";
    }

    @GetMapping("/{id}")
    public String displayInvoice(@PathVariable("id") String number, Model model) {

        model.addAttribute("invoice", invoiceService.getInvoiceByNumber(number));

        return "invoice-details";
    }

    @GetMapping("/create-form")
    public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoice) {
        return "invoice-create-form";
    }
}

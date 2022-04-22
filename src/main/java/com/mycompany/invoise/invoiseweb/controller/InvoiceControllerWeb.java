package com.mycompany.invoise.invoiseweb.controller;


import com.mycompany.invoise.core.repository.controller.IInvoiceController;
import com.mycompany.invoise.core.repository.model.Invoice;
import com.mycompany.invoise.core.repository.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class InvoiceControllerWeb implements IInvoiceController {

    @Autowired
    private IInvoiceService invoiceService;

    public IInvoiceService getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public void createInvoice() {
        var customerName = "Tesla";

        var invoice = new Invoice();
        invoice.setCustomerName(customerName);

        invoiceService.createInvoice(invoice);
    }

    @RequestMapping("invoice-home")
    public @ModelAttribute("invoices") List<Invoice> displayHome() {
        System.out.println("methode appelée!");

        return invoiceService.getInvoiceList();
    }
}

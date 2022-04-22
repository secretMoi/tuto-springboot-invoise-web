package com.mycompany.invoise.invoiseweb.controller;


import com.mycompany.invoise.core.controller.IInvoiceController;
import com.mycompany.invoise.core.model.Invoice;
import com.mycompany.invoise.core.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class InvoiceControllerWeb implements IInvoiceController {

    @Autowired
    private IInvoiceService invoiceService;

    public void setInvoiceService(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public String createInvoice(@ModelAttribute Invoice invoice) {

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
    public String displayInvoiceCreateForm(@ModelAttribute Invoice invoice) {
        return "invoice-create-form";
    }
}

package com.mycompany.invoise.invoiseweb.controller;
import com.mycompany.invoise.core.entity.customer.Address;
import com.mycompany.invoise.core.entity.customer.Customer;
import com.mycompany.invoise.core.entity.invoice.Invoice;

import com.mycompany.invoise.invoice.service.InvoiceServiceInterface;
import com.mycompany.invoise.invoiseweb.form.InvoiceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
@RequestMapping("/invoice")
public class InvoiceControllerWeb {

    @Autowired
    private final InvoiceServiceInterface invoiceService;

    public InvoiceControllerWeb(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }

    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }


    @GetMapping("/home")
    public String displayHome(Model model){
        System.out.println("La méthode displayHome a été appelé");
        model.addAttribute("invoices", invoiceService.getInvoiceList());
        return "invoice-home";
    }

/*
    @GetMapping("/{id}")
    public String detailsInvoice(@PathVariable("id")String number, Model model){
        System.out.println("La méthode displayInvoice a été invoqué");
        model.addAttribute("invoice", invoiceService.getInvoiceByNumber(number));
        return "invoice-details";
    }
*/

    @GetMapping("/create-form")
    public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoice){
        return "invoice-create-form";
    }




    @PostMapping("/create")
    public String createInvoice(@Valid @ModelAttribute InvoiceForm invoiceForm, BindingResult results){

        if(results.hasErrors()){
            return "invoice-create-form";
        }

        Invoice invoice = new Invoice();
        Customer customer = new Customer(invoiceForm.getCustomerName());
        invoice.setCustomer(customer);
        Address address = new Address(invoiceForm.getStreetName(),invoiceForm.getStreetNumber(),invoiceForm.getCity(),invoiceForm.getZipCode(),invoiceForm.getCountry());
        customer.setAddress(address);
        invoice.setOrderNumber(invoiceForm.getOrderNumber());
        invoiceService.createInvoice(invoice);
        return "invoice-created";
    }


}

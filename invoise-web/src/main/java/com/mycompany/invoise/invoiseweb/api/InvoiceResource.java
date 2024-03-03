package com.mycompany.invoise.invoiseweb.api;

import com.mycompany.invoise.core.entity.invoice.Invoice;
import com.mycompany.invoise.invoice.service.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceResource {

    @Autowired
    private final InvoiceServiceInterface invoiceService;

    public InvoiceResource(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }

    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    @GetMapping
    public Iterable<Invoice> displayHome(){
        System.out.println("La méthode displayHome a été appelé");
        return invoiceService.getInvoiceList();
    }

    @GetMapping("/{id}")
    public Invoice get(@PathVariable("id")String number){
        System.out.println("La méthode displayInvoice a été invoqué");
        return invoiceService.getInvoiceByNumber(number);
    }

    @PostMapping("")
    public Invoice create(@RequestBody Invoice invoice){
        return invoiceService.createInvoice(invoice);
    }


     /*
    @GetMapping("/create-form")
    public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoice){
        return "invoice-create-form";
    }
    */


}

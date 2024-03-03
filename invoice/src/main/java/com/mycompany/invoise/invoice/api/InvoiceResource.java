package com.mycompany.invoise.invoice.api;

import com.mycompany.invoise.core.entity.customer.Address;
import com.mycompany.invoise.core.entity.customer.Customer;
import com.mycompany.invoise.core.entity.invoice.Invoice;
import com.mycompany.invoise.invoice.service.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/invoice")
public class InvoiceResource {


    @Autowired
    private final InvoiceServiceInterface invoiceService;

    @Autowired
    private WebClient.Builder webClientBuilder;


    public InvoiceResource(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }

    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    @GetMapping
    public ParallelFlux<Invoice> list(){
        System.out.println("La méthode displayHome a été appelé");

        List<Mono<Invoice>> invoiceMono = new ArrayList<>();

        final WebClient webClient = webClientBuilder.baseUrl("http://customer-service").build();

        Iterable<Invoice> invoices = invoiceService.getInvoiceList();
        invoices.forEach(invoice-> {
            invoiceMono.add(webClient.get()
                    .uri("/customer/"+invoice.getIdCustomer())
                    .retrieve()
                    .bodyToMono(Customer.class)
                    .map(customer -> {
                        invoice.setCustomer(customer);
                        return invoice;
                    })
            );
        });

        final Flux<Invoice> invoiceFlux = Flux.concat(invoiceMono);
        return invoiceFlux.parallel().runOn(Schedulers.elastic());
    }


    @GetMapping("/{id}")
    public Invoice get(@PathVariable("id")String number){
        System.out.println("La méthode displayInvoice a été invoqué");
        Invoice invoice = invoiceService.getInvoiceByNumber(number);

        final WebClient webClient = webClientBuilder.baseUrl("http://customer-service").build();

        final Customer customer = webClient.get().uri("/customer/"+invoice.getIdCustomer())
                .retrieve()
                .bodyToMono(Customer.class)
                .block();

        final Address address = webClient.get().uri("/address/"+customer.getAddress().getId())
                .retrieve()
                .bodyToMono(Address.class)
                .block();

        customer.setAddress(address);
        invoice.setCustomer(customer);
        return invoice;
    }



    /*
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

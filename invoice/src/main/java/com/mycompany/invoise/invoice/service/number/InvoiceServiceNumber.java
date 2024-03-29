package com.mycompany.invoise.invoice.service.number;

import com.mycompany.invoise.core.entity.invoice.Invoice;

//import com.mycompany.invoise.invoice.repository.CustomerRepositoryInterface;
import com.mycompany.invoise.invoice.repository.InvoiceRepositoryInterface;
import com.mycompany.invoise.invoice.service.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceServiceNumber implements InvoiceServiceInterface {

    @Autowired
    private InvoiceRepositoryInterface invoiceRepository;

   /* @Autowired
    private CustomerRepositoryInterface customerRepository; */

    public InvoiceServiceNumber(InvoiceRepositoryInterface invoiceRepository/*, CustomerRepositoryInterface customerRepository*/) {
        this.invoiceRepository = invoiceRepository;
      //  this.customerRepository = customerRepository;
    }

  /*  public CustomerRepositoryInterface getCustomerRepository() {
        return customerRepository;
    }
*/
    public InvoiceRepositoryInterface getInvoiceRepository() {
        return invoiceRepository;
    }

  /*  public void setCustomerRepository(CustomerRepositoryInterface customerRepository) {
        this.customerRepository = customerRepository;
    }
  */
    @Override
    public Iterable<Invoice> getInvoiceList() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceByNumber(String number) {
        return invoiceRepository.findById(number).orElseThrow();
    }

    @Transactional
    public Invoice createInvoice(Invoice invoice){
       // customerRepository.save(invoice.getCustomer());
        return invoiceRepository.save(invoice);
    }

}
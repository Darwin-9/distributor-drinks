package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

import com.sena.crud_basic.model.Distributor;
import com.sena.crud_basic.model.Store;




public class OrderDTO {

    private StoreDTO store;
    private DistributorDTO distributor;
    private LocalDateTime order_date;

    public OrderDTO(StoreDTO store, DistributorDTO distributor, LocalDateTime order_date) {
        this.store = store;
        this.distributor = distributor;
        this.order_date = order_date;
    }

   

    public OrderDTO(Store store2, Distributor distributor2, LocalDateTime order_date2) {
        //TODO Auto-generated constructor stub
    }



    public StoreDTO getStore() {
        return store;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }

    public DistributorDTO getDistributor() {
        return distributor;
    }

    public void setDistributor(DistributorDTO distributor) {
        this.distributor = distributor;
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }
}

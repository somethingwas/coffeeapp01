package com.example.coffeeapp01;

public class Order {
    private int _id;
    private String _custName;
    private int _saleAmount;

    public Order() {
        _id = 0;
        _custName = null;
        _saleAmount = 0;
    }


    public Order(String _custName, int _salesAmount) {
        this._custName = _custName;
        this._saleAmount = _salesAmount;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_custName() {
        return _custName;
    }

    public void set_custName(String _custName) {
        this._custName = _custName;
    }

    public int get_saleAmount() {
        return _saleAmount;
    }

    public void set_saleAmount(int _salesAmount) {
        this._saleAmount = _salesAmount;
    }
}

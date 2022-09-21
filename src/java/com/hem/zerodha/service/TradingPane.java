package com.hem.zerodha.service;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;


public class TradingPane extends GridPane {
    private TableView<Instrument> table = new TableView<Instrument>();

    public TradingPane() {
        GridPane toolbar = new GridPane();

        Button plusOne = new Button("1+");

        CheckBox buySell = new CheckBox("B/S");

        CheckBox market = new CheckBox("M/L");

        TextField price = new TextField();

        toolbar.add(plusOne, 0, 0);
        toolbar.add(buySell, 1, 0);
        toolbar.add(market, 2, 0);
        toolbar.add(price, 3, 0);

        TableColumn nameCol = new TableColumn("Name");
        TableColumn ltpCol = new TableColumn("LTP");
        TableColumn quantityCol = new TableColumn("Quantity");
        TableColumn pnlCol = new TableColumn("PNL");


        nameCol.setCellValueFactory(
                new PropertyValueFactory<Instrument,String>("tradingSymbol")
        );
        ltpCol.setCellValueFactory(
                new PropertyValueFactory<Instrument,String>("ltp")
        );
        pnlCol.setCellValueFactory(
                new PropertyValueFactory<Instrument,String>("pnl")
        );
        quantityCol.setCellValueFactory(
                new PropertyValueFactory<Instrument,String>("quantity")
        );

        ObservableList<Instrument> tableModel = ZerodhaService.getTableModel();
        table.setItems(tableModel);

        table.getColumns().addAll(nameCol, ltpCol, quantityCol, pnlCol);


        this.add(toolbar, 0,0);
        this.add(table, 0,1);

    }
}

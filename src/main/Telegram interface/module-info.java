module com.example.inferno_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.google.gson;
    requires org.slf4j;
    requires telegrambots.meta;
    requires telegrambots;
    requires com.rometools.rome;
    requires annotations;
    requires java.validation;

    opens com.example.inferno_fx.OperazioniJSON to com.google.gson;
    opens ZonaFeedConClassi to com.google.gson;
    exports com.example.inferno_fx;
    opens com.example.inferno_fx to com.google.gson, javafx.fxml;
}
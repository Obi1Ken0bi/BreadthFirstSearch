module ru.puzikov.algorithms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires lombok;
    requires org.testng;
    requires junit;

    opens ru.puzikov.algorithms to javafx.fxml;
    exports ru.puzikov.algorithms;
}
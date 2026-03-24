package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tour.Tour;

/**
 * Panel containing the list of tours.
 */
public class TourListPanel extends UiPart<Region> {
    private static final String FXML = "TourListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TourListPanel.class);

    @FXML
    private ListView<Tour> tourListView;

    /**
     * Creates a {@code TourListPanel} with the given {@code ObservableList}.
     */
    public TourListPanel(ObservableList<Tour> tourList) {
        super(FXML);
        tourListView.setItems(tourList);
        tourListView.setCellFactory(listView -> new TourListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Tour} using a {@code TourCard}.
     */
    class TourListViewCell extends ListCell<Tour> {
        @Override
        protected void updateItem(Tour tour, boolean empty) {
            super.updateItem(tour, empty);

            if (empty || tour == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TourCard(tour, getIndex() + 1).getRoot());
            }
        }
    }

}

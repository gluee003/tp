package seedu.address.model.tour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOUR_NAME_JAMES;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES;
import static seedu.address.testutil.TypicalContacts.TOUR_JAMES_JR;

import org.junit.jupiter.api.Test;

public class TourTest {

    @Test
    public void equals() {
        Tour james = TOUR_JAMES;
        Tour jamesJr = TOUR_JAMES_JR;

        // same values -> returns true
        Tour jamesCopy = new Tour(VALID_TOUR_NAME_JAMES);
        assertTrue(james.equals(jamesCopy));

        // same object -> returns true
        assertTrue(james.equals(james));

        // null -> returns false
        assertFalse(james.equals(null));

        // different type -> returns false
        assertFalse(james.equals(5));

        // different tour -> returns false
        assertFalse(james.equals(jamesJr));
    }

    @Test
    public void toStringMethod() {
        Tour james = new Tour(VALID_TOUR_NAME_JAMES);
        assertEquals(VALID_TOUR_NAME_JAMES, james.toString());
    }
}

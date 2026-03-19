package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactIsInTourPredicate;
import seedu.address.model.tour.Tour;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewTourCommand}.
 */
public class ViewTourCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ContactIsInTourPredicate firstPredicate = new ContactIsInTourPredicate(new Tour("walking tour"));
        ContactIsInTourPredicate secondPredicate = new ContactIsInTourPredicate(new Tour("food tour"));

        ViewTourCommand viewFirstCommand = new ViewTourCommand(firstPredicate);
        ViewTourCommand viewSecondCommand = new ViewTourCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewTourCommand viewFirstCommandCopy = new ViewTourCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different tour -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void execute_noContactsInTour_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        ContactIsInTourPredicate predicate = new ContactIsInTourPredicate(new Tour("nonexistent tour"));
        ViewTourCommand command = new ViewTourCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_tourWithContacts_contactsFound() {
        // Add contacts assigned to a specific tour to a fresh model
        Tour targetTour = new Tour("walking tour");
        Contact alice = new PersonBuilder().withName("Alice").withTours("walking tour").build();
        Contact bob = new PersonBuilder().withName("Bob").withTours("walking tour").build();
        Contact charlie = new PersonBuilder().withName("Charlie").withTours("food tour").build();

        seedu.address.model.AddressBook ab = new seedu.address.model.AddressBook();
        ab.addContact(alice);
        ab.addContact(bob);
        ab.addContact(charlie);

        Model testModel = new ModelManager(ab, new UserPrefs());
        Model testExpectedModel = new ModelManager(ab, new UserPrefs());

        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2);
        ContactIsInTourPredicate predicate = new ContactIsInTourPredicate(targetTour);
        ViewTourCommand command = new ViewTourCommand(predicate);
        testExpectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, testModel, expectedMessage, testExpectedModel);
        assertEquals(Arrays.asList(alice, bob), testModel.getFilteredContactList());
    }

    @Test
    public void toStringMethod() {
        ContactIsInTourPredicate predicate = new ContactIsInTourPredicate(new Tour("walking tour"));
        ViewTourCommand viewTourCommand = new ViewTourCommand(predicate);
        String expected = ViewTourCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, viewTourCommand.toString());
    }
}

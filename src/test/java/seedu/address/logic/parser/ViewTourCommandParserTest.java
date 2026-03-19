package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewTourCommand;
import seedu.address.model.contact.ContactIsInTourPredicate;
import seedu.address.model.tour.Tour;

public class ViewTourCommandParserTest {

    private ViewTourCommandParser parser = new ViewTourCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTourCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewTourCommand() {
        ViewTourCommand expectedCommand =
                new ViewTourCommand(new ContactIsInTourPredicate(new Tour("walking tour")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "walking tour", expectedCommand);

        // leading and trailing whitespaces trimmed
        assertParseSuccess(parser, "  walking tour  ", expectedCommand);
    }
}

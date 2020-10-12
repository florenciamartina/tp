package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS1010S;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS50;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS1010S;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS50;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS50;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CS50;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.CS1010S;
import static seedu.address.testutil.TypicalModules.CS2103;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.ModuleBuilder;

public class AddModCommandParserTest {
    private AddModCommandParser parser = new AddModCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModuleCs1010s = new ModuleBuilder(CS1010S).withInstructors().build();
        Module expectedModuleCs2103 = new ModuleBuilder(CS2103).withInstructors().build();

        assertParseSuccess(parser, CODE_DESC_CS1010S + NAME_DESC_CS1010S,
                new AddModCommand(expectedModuleCs1010s));

        assertParseSuccess(parser, CODE_DESC_CS2103 + NAME_DESC_CS2103,
                new AddModCommand(expectedModuleCs2103));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero persons
        Module expectedModule = new ModuleBuilder(CS1010S).withInstructors().build();
        assertParseSuccess(parser, CODE_DESC_CS1010S + NAME_DESC_CS1010S,
            new AddModCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModCommand.MESSAGE_USAGE);

        // missing code prefix
        assertParseFailure(parser, VALID_MODULE_CODE_CS50 + NAME_DESC_CS50,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, CODE_DESC_CS50 + VALID_MODULE_NAME_CS50,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, CODE_DESC_CS50 + INVALID_MODULE_NAME_DESC,
                ModuleName.MESSAGE_CONSTRAINTS);

        // invalid code
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC + NAME_DESC_CS50,
                ModuleCode.MESSAGE_CONSTRAINTS);

    }
}

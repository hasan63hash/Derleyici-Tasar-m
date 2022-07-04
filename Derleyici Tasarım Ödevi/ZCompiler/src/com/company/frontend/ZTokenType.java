package com.company.frontend;

import java.util.HashSet;
import java.util.Hashtable;

public enum ZTokenType {
    // Ayrılmış kelimeler
    AND, TRUE, FALSE, FOR, İF, ELSE, ELSEIF, OR, VAR, PRT, ENTER,

    // Özel semboller
    PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), SEMICOLON(";"), COMMA(","), QUOTE("'"), NOT("!"),
    IS_EQUALS("=="), NOT_EQUALS("!="), INCREASE("++"), EQUALS("="), LESS_THAN("<"), LESS_EQUALS("<="),
    GREATER_EQUALS(">="), GREATER_THAN(">"), LEFT_PAREN("("), RIGHT_PAREN(")"),
    LEFT_BRACE("{"), RIGHT_BRACE("}"),

    IDENTIFIER, INTEGER, STRING,
    ERROR, END_OF_FILE;

    private static final int
            FIRST_RESERVED_INDEX = AND.ordinal();
    private static final int
            LAST_RESERVED_INDEX = ENTER.ordinal();
    private static final int
            FIRST_SPECIAL_INDEX = PLUS.ordinal();
    private static final int
            LAST_SPECIAL_INDEX = RIGHT_BRACE.ordinal();

    private String text; // token text

    ZTokenType()
    {
        this.text = this.toString().toLowerCase();
    }

    ZTokenType(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public static HashSet<String> RESERVED_WORDS = new HashSet<String>();
    static {
        ZTokenType values[] = ZTokenType.values();
        for (int i = FIRST_RESERVED_INDEX; i <= LAST_RESERVED_INDEX; ++i) {
            RESERVED_WORDS.add(values[i].getText().toLowerCase());
        }
    }

    public static Hashtable<String, ZTokenType> SPECIAL_SYMBOLS = new Hashtable<String, ZTokenType>();
    static {
        ZTokenType values[] = ZTokenType.values();
        for (int i = FIRST_SPECIAL_INDEX; i <= LAST_SPECIAL_INDEX; ++i) {
            SPECIAL_SYMBOLS.put(values[i].getText(), values[i]);
        }
    }
}

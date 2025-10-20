package com.example.databaseauth.Utils;

public class Transform {
    public static int parseIntOrDefault(String parse, int defaultValue)
    {
        int parseValue;
        try
        {
            parseValue = Integer.parseInt(parse);
        }
        catch (NumberFormatException ex)
        {
            parseValue = defaultValue;
        }
        return parseValue;
    }
}

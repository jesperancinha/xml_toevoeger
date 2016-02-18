package com.steelzack.xmladder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XmlAdderMain {

    public static void main(String[] args) throws IOException {
        final String sourceDirectory = args[0];
        final String destinationDirectory = args[1];
        final String addAttributesFile = args[2];

        final File fileSourceDirectory = new File(sourceDirectory);
        final File fileDestinationDirectory = new File(destinationDirectory);
        final InputStream fileAddAtributesFile = new FileInputStream(new File(addAttributesFile));

        final XmlAdderManager manager = new XmlAdderManager( //
                fileSourceDirectory, //
                fileDestinationDirectory, //
                fileAddAtributesFile //
        );

    }
}
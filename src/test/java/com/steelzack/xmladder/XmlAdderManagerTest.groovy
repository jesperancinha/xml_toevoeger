package com.steelzack.xmladder

import com.steelzack.xmladder.instruction.XmlAdderAddAttributeManager
import com.steelzack.xmladder.instruction.XmlAdderInstruction
import org.junit.Assert
import org.junit.Test
import org.junit.rules.TemporaryFolder

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
class XmlAdderManagerTest {

    @Test
    void testReadAllAddAttributes() {
        final InputStream is = getClass().getResourceAsStream("testReadAttributeBean.csv");
        final XmlAdderManager manager = new XmlAdderManager(null, null, is)

        final XmlAdderAddAttributeManager attributeManager = manager.getAddAttributeManager();
        final Map<String, XmlAdderInstruction> result = attributeManager.getXmlAdderInstructionArrayMap();
        final resultSet = result.keySet()

        Assert.assertEquals(1, resultSet.size());
        for (String key : resultSet) {
            Assert.assertEquals("testnode1/testnode2", key);
            Assert.assertEquals("attribute1value", result.get(key).getAttributesToAdd().get("attribute1name"));
        }
    }

    @Test
    void testlistAllFilesToChange_empty() {
        final List<File> resultFilesToChange = XmlAdderManager.listAllFilesToChange(getTestFolder(false));

        Assert.assertEquals(0, resultFilesToChange.size());
    }

    @Test
    void testlistAllFilesToChange_oneXmlInEachFolder() {
        final List<File> resultFilesToChange = XmlAdderManager.listAllFilesToChange(getTestFolder(true));

        Assert.assertEquals(6, resultFilesToChange.size());
        Assert.assertEquals("file1.xml", resultFilesToChange.get(0).getName());
        Assert.assertEquals("file11.xml", resultFilesToChange.get(1).getName());
        Assert.assertEquals("file12.xml", resultFilesToChange.get(2).getName());
        Assert.assertEquals("file2.xml", resultFilesToChange.get(3).getName());
        Assert.assertEquals("file21.xml", resultFilesToChange.get(4).getName());
        Assert.assertEquals("file22.xml", resultFilesToChange.get(5).getName());
    }


    private File getTestFolder(boolean addFiles) {
        final TemporaryFolder folder = new TemporaryFolder();
        folder.create();
        final File structure = folder.newFolder("structure");
        final File folder1 = new File(structure, "folder1");
        folder1.deleteOnExit();
        folder1.mkdir();
        final File folder11 = new File(folder1, "folder11");
        folder11.deleteOnExit();
        folder11.mkdir();
        final File folder12 = new File(folder1, "folder12");
        folder12.deleteOnExit();
        folder12.mkdir();
        final File folder2 = new File(structure, "folder2");
        folder2.deleteOnExit();
        folder2.mkdir();
        final File folder21 = new File(folder2, "folder21");
        folder21.deleteOnExit();
        folder21.mkdir();
        final File folder22 = new File(folder2, "folder22");
        folder22.deleteOnExit();
        folder22.mkdir();

        if(addFiles)
        {
            createFile(folder1, "file1");
            createFile(folder11, "file11");
            createFile(folder12, "file12");
            createFile(folder2, "file2");
            createFile(folder21, "file21");
            createFile(folder22, "file22");

        }
        return structure;
    }

    private void createFile(File folder, String fileName) {
        final File file1 = new File(folder, fileName + ".xml");
        file1.deleteOnExit();
        file1.createNewFile();
    }
}
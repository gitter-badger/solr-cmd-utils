package de.tblsoft.solr.pipeline.pipeline;

import de.tblsoft.solr.pipeline.test.AbstractPipelineTest;
import org.junit.Test;

/**
 * Created by tblsoft on 28.04.16.
 */
public class CsvReaderTest extends AbstractPipelineTest {

    @Test
    public void testCsvReader() {
        runPipeline("examples/unittest/csv-reader-pipeline.yaml");
        assertFiled("column1", "foo");
        assertFiled("column2", "bar");

        assertNumberOfDocuments(2);
        assertNumberOfFields(2);

        assertInitWasDelegated();
        assertEndWasDelegated();
    }

    @Test
    public void testCsvReaderWithGzip() {
        runPipeline("examples/unittest/csv-reader-gzip-pipeline.yaml");
        assertFiled("column1", "foo");
        assertFiled("column2", "bar");

        assertNumberOfDocuments(2);
        assertNumberOfFields(2);

        assertInitWasDelegated();
        assertEndWasDelegated();
    }

    @Test
    public void testCsvReaderWithoutHeader() {
        runPipeline("examples/unittest/csv-reader-without-header-pipeline.yaml");
        assertFiled("column1", "foo");
        assertFiled("column2", "bar");

        assertNumberOfDocuments(2);
        assertNumberOfFields(2);
    }

    @Test(expected = RuntimeException.class)
    public void testFileNotExists() {
        runPipeline("examples/unittest/csv-file-not-exists-pipeline.yaml");
    }
}
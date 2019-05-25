import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TemplateTrial {

    public static void main(String[] args) throws Exception {

        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setDirectoryForTemplateLoading(new File("./src/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);

        /* ------------------------------------------------------------------------ */
        /* You usually do these for MULTIPLE TIMES in the application life-cycle:   */

        /* Create a data-model */
        Map root = new HashMap();
        root.put("user", "Big Joe");
        Product latest = new Product();
        latest.setUrl("products/greenmouse.html");
        latest.setName("green mouse");
        root.put("latestProduct", latest);

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("test.ftlh");

        /* Merge data-model with template */
//        Writer out = new OutputStreamWriter(System.out);
        Writer out = new StringWriter();
        temp.process(root, out);
        System.out.println(out.toString());
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.
    }
}


class DataModelTest {
    public String user;
    public Product latestProduct;

    public DataModelTest(String user, Product latestProduct) {
        this.user = user;
        this.latestProduct = latestProduct;
    }
}